package br.ufsm.csi.aulaspringmvc.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AutorizadorInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String url = request.getRequestURI();

        if (url.equals("/") || url.equals("/login")) {
            return true;
        }

        if (request.getSession().getAttribute("usuarioLogado") == null) {
            response.sendRedirect("/");
            return false;
        }

        return true;
    }
}
