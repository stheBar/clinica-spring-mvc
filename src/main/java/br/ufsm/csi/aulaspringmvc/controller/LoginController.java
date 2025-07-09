package br.ufsm.csi.aulaspringmvc.controller;

import br.ufsm.csi.aulaspringmvc.model.Medico;
import br.ufsm.csi.aulaspringmvc.model.Paciente;
import br.ufsm.csi.aulaspringmvc.model.Usuario;
import br.ufsm.csi.aulaspringmvc.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(HttpSession session, Model model, String email, String senha) {
        try {
            Usuario usuario = loginService.autenticar(email, senha);

            if (usuario != null) {

                session.setAttribute("usuarioLogado", usuario);

                if (usuario.isAdmin()) {
                    return "redirect:/admin";

                } else if (usuario instanceof Medico) {
                    return "redirect:/medico";

                } else if (usuario instanceof Paciente) {
                    return "redirect:/paciente";
                } else {
                    return "redirect:/";
                }

            } else {
                model.addAttribute("msg", "Login ou senha incorreto!");
                return "index";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "Ocorreu um erro inesperado. Tente novamente.");
            return "index";
        }
    }
}