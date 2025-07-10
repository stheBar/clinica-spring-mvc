package br.ufsm.csi.aulaspringmvc.controller;

import br.ufsm.csi.aulaspringmvc.model.Consulta;
import br.ufsm.csi.aulaspringmvc.model.Medico;
import br.ufsm.csi.aulaspringmvc.model.Paciente;
import br.ufsm.csi.aulaspringmvc.service.MedicoService;
import br.ufsm.csi.aulaspringmvc.service.PacienteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final br.ufsm.csi.aulaspringmvc.service.ConsultaService consultaService;
    private final MedicoService medicoService;
    private final PacienteService pacienteService;

    private static final DateTimeFormatter FORM_LOCAL = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    private static final DateTimeFormatter FORM_LABEL = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Autowired
    public AdminController(br.ufsm.csi.aulaspringmvc.service.ConsultaService consultaService, MedicoService medicoService, PacienteService pacienteService) {
        this.consultaService = consultaService;
        this.medicoService = medicoService;
        this.pacienteService = pacienteService;
    }

    private boolean isUserLoggedIn(HttpSession session) {
        return session.getAttribute("usuarioLogado") != null;
    }

    @GetMapping({"/", ""})
    public String adminHome(HttpSession session) {
        return "redirect:/admin/consulta";
    }

    //CONSULTA

    @GetMapping("/consulta")
    public String listarConsultas(Model model, HttpSession session) throws Exception {
        if (!isUserLoggedIn(session)) return "redirect:/";

        Map<Integer, String> horarioMap = new HashMap<>();
        consultaService.listarTodas().forEach(c ->
                horarioMap.put(c.getId(), c.getDataHorario().format(FORM_LABEL))
        );

        model.addAttribute("consultas", consultaService.listarTodas());
        model.addAttribute("horarioMap", horarioMap);
        return "pages/admin/consulta"; // O nome do seu arquivo JSP
    }

    @GetMapping({"/consulta/form", "/consulta/form/{id}"})
    public String exibirFormularioConsulta(@PathVariable(required = false) Integer id, Model model, HttpSession session) throws Exception {

        Consulta consulta = new Consulta();
        String dataHorarioValue = "";

        if (id != null) {
            consulta = consultaService.buscarPorId(id);
            dataHorarioValue = consulta.getDataHorario().format(FORM_LOCAL);
        }

        model.addAttribute("consulta", consulta);
        model.addAttribute("dataHorarioValue", dataHorarioValue);
        model.addAttribute("medicos", medicoService.listarTodos());
        model.addAttribute("pacientes", pacienteService.listarTodos());
        return "pages/admin/consulta_form";
    }

    @PostMapping("/consulta/save")
    public String salvarConsulta(@ModelAttribute Consulta consulta, RedirectAttributes redirectAttributes, HttpSession session) throws Exception {

        boolean isNew = consulta.getId() == 0;
        Integer idParaIgnorar = isNew ? null : consulta.getId();

        boolean conflitoMedico = consultaService.existeConflitoHorario(
                consulta.getMedico().getId(), consulta.getDataHorario(), idParaIgnorar);
        boolean conflitoPaciente = consultaService.existeConflitoHorarioPaciente(
                consulta.getPaciente().getId(), consulta.getDataHorario(), idParaIgnorar);

        if (conflitoMedico || conflitoPaciente) {
            String erro = conflitoMedico
                    ? "Erro: Já existe uma consulta marcada para este médico no horário selecionado."
                    : "Erro: O paciente já possui uma consulta marcada no horário selecionado.";
            redirectAttributes.addFlashAttribute("msg_error", erro);
            redirectAttributes.addFlashAttribute("consulta", consulta);
            return "redirect:/admin/consulta/form" + (isNew ? "" : "/" + consulta.getId());
        }

        String msg_success;
        if (isNew) {
            consultaService.criarConsulta(consulta);
            msg_success = "Consulta criada com sucesso!";
        } else {
            consultaService.atualizarConsulta(consulta);
            msg_success = "Consulta atualizada com sucesso!";
        }

        redirectAttributes.addFlashAttribute("msg_success", msg_success);
        return "redirect:/admin/consulta";
    }

    @PostMapping("/consulta/excluir/{id}")
    public String excluirConsulta(@PathVariable int id, RedirectAttributes redirectAttributes, HttpSession session) throws Exception {

        consultaService.removerConsulta(id);
        redirectAttributes.addFlashAttribute("msg_success", "Consulta removida com sucesso!");
        return "redirect:/admin/consulta";
    }

    //MÉDICO

    @GetMapping("/medico")
    public String listarMedicos(Model model, HttpSession session) throws Exception {

        model.addAttribute("medicos", medicoService.listarTodos());
        return "pages/admin/medico";
    }

    @GetMapping({"/medico/form", "/medico/form/{id}"})
    public String exibirFormularioMedico(@PathVariable(required = false) Integer id, Model model, HttpSession session) throws Exception {

        Medico medico = (id != null) ? medicoService.buscarPorId(id) : new Medico();
        model.addAttribute("medico", medico);
        return "pages/admin/medico_form";
    }

    @PostMapping("/medico/save")
    public String salvarMedico(@ModelAttribute Medico medico, RedirectAttributes redirectAttributes, HttpSession session) throws Exception {

        String msg_success;
        boolean isNew = medico.getId() == 0;

        if (isNew) {
            medicoService.criarMedico(medico);
            msg_success = "Médico criado com sucesso!";
        } else {
            if (medico.getSenha() == null || medico.getSenha().isEmpty()) {
                Medico medicoExistente = medicoService.buscarPorId(medico.getId());
                medico.setSenha(medicoExistente.getSenha());
            }
            medicoService.atualizarMedico(medico);
            msg_success = "Médico atualizado com sucesso!";
        }

        redirectAttributes.addFlashAttribute("msg_success", msg_success);
        return "redirect:/admin/medico";
    }

    @PostMapping("/medico/excluir/{id}")
    public String excluirMedico(@PathVariable int id, RedirectAttributes redirectAttributes, HttpSession session) throws Exception {

        medicoService.removerMedico(id);
        redirectAttributes.addFlashAttribute("msg_success", "Médico removido com sucesso!");
        return "redirect:/admin/medico";
    }


    //PACIENTE

    @GetMapping("/paciente")
    public String listarPacientes(Model model, HttpSession session) throws Exception {

        model.addAttribute("pacientes", pacienteService.listarTodos());
        return "pages/admin/paciente";
    }

    @GetMapping({"/paciente/form", "/paciente/form/{id}"})
    public String exibirFormularioPaciente(@PathVariable(required = false) Integer id, Model model, HttpSession session) throws Exception {

        Paciente paciente = (id != null) ? pacienteService.buscarPorId(id) : new Paciente();
        model.addAttribute("paciente", paciente);
        return "pages/admin/paciente_form";
    }

    @PostMapping("/paciente/save")
    public String salvarPaciente(@ModelAttribute Paciente paciente, RedirectAttributes redirectAttributes, HttpSession session) throws Exception {

        String msg_success;
        boolean isNew = paciente.getId() == 0;

        if (isNew) {
            pacienteService.criarPaciente(paciente);
            msg_success = "Paciente criado com sucesso!";
        } else {
            if (paciente.getSenha() == null || paciente.getSenha().isEmpty()) {
                Paciente pacienteExistente = pacienteService.buscarPorId(paciente.getId());
                paciente.setSenha(pacienteExistente.getSenha());
            }
            pacienteService.atualizarPaciente(paciente);
            msg_success = "Paciente atualizado com sucesso!";
        }

        redirectAttributes.addFlashAttribute("msg_success", msg_success);
        return "redirect:/admin/paciente";
    }

    @PostMapping("/paciente/excluir/{id}")
    public String excluirPaciente(@PathVariable int id, RedirectAttributes redirectAttributes, HttpSession session) throws Exception {

        pacienteService.removerPaciente(id);
        redirectAttributes.addFlashAttribute("msg_success", "Paciente removido com sucesso!");
        return "redirect:/admin/paciente";
    }
}