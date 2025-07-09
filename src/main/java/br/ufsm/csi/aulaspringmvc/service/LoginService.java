package br.ufsm.csi.aulaspringmvc.service;


import br.ufsm.csi.aulaspringmvc.dao.MedicoDAO;
import br.ufsm.csi.aulaspringmvc.dao.PacienteDAO;
import br.ufsm.csi.aulaspringmvc.dao.UsuarioDAO;
import br.ufsm.csi.aulaspringmvc.model.Admin;
import br.ufsm.csi.aulaspringmvc.model.Medico;
import br.ufsm.csi.aulaspringmvc.model.Paciente;
import br.ufsm.csi.aulaspringmvc.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UsuarioDAO usuarioDAO  = new UsuarioDAO();
    private final MedicoDAO medicoDAO   = new MedicoDAO();
    private final PacienteDAO pacienteDAO = new PacienteDAO();


    public Usuario autenticar(String email, String senha) throws Exception {

        //busca na tabela usuario
        Usuario base = usuarioDAO.findByEmailAndSenha(email, senha);
        if (base == null) return null;

        int id = base.getId();

        //verifica se existe entrada em medico/paciente
        Medico m = medicoDAO.findById(id);      // devolve null se não existir
        if (m != null) return m;

        Paciente p = pacienteDAO.findById(id);    // idem
        if (p != null) return p;

        //se admin=true devolve um Admin
        if (base.isAdmin()) {
            Admin a = new Admin();
            a.setId(base.getId());
            a.setEmail(base.getEmail());
            a.setSenha(base.getSenha());
            a.setNome(base.getNome());
            a.setCpf(base.getCpf());
            a.setAdmin(true);
            return a;
        }

        //devolve o próprio base
        return base;
    }
}
