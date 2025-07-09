package br.ufsm.csi.aulaspringmvc.service;
import br.ufsm.csi.aulaspringmvc.dao.PacienteDAO;
import br.ufsm.csi.aulaspringmvc.model.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {
    private final PacienteDAO dao = new PacienteDAO();

    public List<Paciente> listarTodos() throws Exception {
        return dao.findAll();
    }

    public Paciente buscarPorId(int id) throws Exception {
        return dao.findById(id);
    }

    public Paciente criarPaciente(Paciente paciente) throws Exception {
        return dao.create(paciente);
    }

    public boolean atualizarPaciente(Paciente paciente) throws Exception {
        return dao.update(paciente);
    }

    public boolean removerPaciente(int id) throws Exception {
        return dao.delete(id);
    }
}
