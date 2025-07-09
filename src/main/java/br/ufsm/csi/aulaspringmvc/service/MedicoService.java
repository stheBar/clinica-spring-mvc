package br.ufsm.csi.aulaspringmvc.service;

import br.ufsm.csi.aulaspringmvc.dao.MedicoDAO;
import br.ufsm.csi.aulaspringmvc.model.Medico;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {
    private final MedicoDAO dao = new MedicoDAO();

    public List<Medico> listarTodos() throws Exception {
        return dao.findAll();
    }
    public Medico buscarPorId(int id) throws Exception {
        return dao.findById(id);
    }

    public Medico criarMedico(Medico medico) throws Exception {
        return dao.create(medico);
    }

    public boolean atualizarMedico(Medico medico) throws Exception {
        return dao.update(medico);
    }

    public boolean removerMedico(int id) throws Exception {
        return dao.delete(id);
    }
}