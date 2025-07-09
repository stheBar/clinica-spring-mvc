package br.ufsm.csi.aulaspringmvc.controller;

import br.ufsm.csi.aulaspringmvc.model.Consulta;
import br.ufsm.csi.aulaspringmvc.model.Paciente;
import br.ufsm.csi.aulaspringmvc.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

    private final ConsultaService consultaService;

    @Autowired
    public PacienteController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping({"", "/"})
    public String pacienteHome(@SessionAttribute(value = "usuarioLogado", required = false) Paciente paciente,
                               Model model) throws Exception {

        List<Consulta> consultas = consultaService.listarPorPaciente(paciente.getId());
        model.addAttribute("consultas", consultas);
        return "pages/paciente/paciente_home";
    }

    @GetMapping("/prontuario")
    public String prontuario(@SessionAttribute(value = "usuarioLogado", required = false) Paciente paciente,
                             Model model) throws Exception {

        if (paciente == null) {
            return "redirect:/login";
        }

        List<Consulta> consultas = consultaService.listarPorPaciente(paciente.getId());
        model.addAttribute("consultas", consultas);
        return "pages/paciente/prontuario";
    }
}
