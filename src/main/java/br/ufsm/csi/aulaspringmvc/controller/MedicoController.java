package br.ufsm.csi.aulaspringmvc.controller;

import br.ufsm.csi.aulaspringmvc.model.Consulta;
import br.ufsm.csi.aulaspringmvc.model.Medico;
import br.ufsm.csi.aulaspringmvc.model.Paciente;
import br.ufsm.csi.aulaspringmvc.service.ConsultaService;
import br.ufsm.csi.aulaspringmvc.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/medico")
public class MedicoController {

    private final ConsultaService consultaService;
    private final PacienteService pacienteService;

    @Autowired
    public MedicoController(ConsultaService consultaService, PacienteService pacienteService) {
        this.consultaService = consultaService;
        this.pacienteService = pacienteService;
    }

    @GetMapping({"/", ""})
    public String medicoHome(@SessionAttribute("usuarioLogado") Medico medico, Model model) throws Exception {
        List<Consulta> consultas = consultaService.listarPorMedico(medico.getId());
        model.addAttribute("consultas", consultas);
        return "pages/medico/medico_home";
    }

    @GetMapping("/editar")
    public String editarConsulta(@RequestParam("id") int id, Model model, @SessionAttribute("usuarioLogado") Medico medico) throws Exception {
        Consulta consulta = consultaService.buscarPorId(id);

        if (consulta == null || consulta.getMedico().getId() != medico.getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Consulta não autorizada.");
        }

        model.addAttribute("consulta", consulta);
        return "pages/medico/editar_consulta";
    }

    @PostMapping("/salvar")
    public String salvarConsulta(@RequestParam("id") int id,
                                 @RequestParam("status") String status,
                                 @RequestParam("observacao") String observacao,
                                 @SessionAttribute("usuarioLogado") Medico medico,
                                 RedirectAttributes redirectAttributes) throws Exception {

        Consulta consulta = consultaService.buscarPorId(id);

        if (consulta == null || consulta.getMedico().getId() != medico.getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Consulta não autorizada.");
        }

        consulta.setStatus(status);
        consulta.setPbservacao(observacao);
        consultaService.atualizarConsulta(consulta);

        redirectAttributes.addFlashAttribute("msgSucesso", "Consulta atualizada com sucesso!");
        return "redirect:/medico";
    }

    @GetMapping("/meusPacientes")
    public String meusPacientes(Model model, @SessionAttribute("usuarioLogado") Medico medico) throws Exception {
        List<Paciente> pacientes = consultaService.listarPacientesAtendidosPorMedico(medico.getId());
        model.addAttribute("pacientes", pacientes);
        return "pages/medico/meus_pacientes";
    }

    @GetMapping("/prontuario")
    public String prontuario(@RequestParam("id") int pacienteId, Model model, @SessionAttribute("usuarioLogado") Medico medico) throws Exception {
        Paciente paciente = pacienteService.buscarPorId(pacienteId);
        if (paciente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado.");
        }

        List<Consulta> todasConsultas = consultaService.listarPorPaciente(pacienteId);

        List<Consulta> historicoComMedico = todasConsultas.stream()
                .filter(c -> c.getMedico().getId() == medico.getId())
                .collect(Collectors.toList());

        if (historicoComMedico.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso não autorizado ao prontuário, pois não há consultas prévias com este paciente.");
        }

        model.addAttribute("paciente", paciente);
        model.addAttribute("consultas", historicoComMedico);
        return "pages/medico/prontuario";
    }
}