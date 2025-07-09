package br.ufsm.csi.aulaspringmvc.service;


import br.ufsm.csi.aulaspringmvc.dao.UsuarioDAO;
import br.ufsm.csi.aulaspringmvc.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioDAO dao = new UsuarioDAO();

    public Usuario criarUsuario(String email,
                                String senha,
                                String nome,
                                String cpf,
                                boolean admin) throws Exception {

        Usuario u = new Usuario();
        u.setEmail(email);
        u.setSenha(senha);
        u.setNome(nome);
        u.setCpf(cpf);
        u.setAdmin(admin);
        return dao.create(u);
    }
}
