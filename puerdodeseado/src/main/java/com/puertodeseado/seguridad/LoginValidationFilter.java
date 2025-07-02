package com.puertodeseado.seguridad;

import com.puertodeseado.EntidadesDTO.seguridad.LoginDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class LoginValidationFilter extends OncePerRequestFilter {

  @Autowired
  private Validator validator;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
          throws ServletException, IOException {
    if (request.getRequestURI().equals("/login") && request.getMethod().equals("POST")) {
      LoginDTO loginDTO = new LoginDTO();
      loginDTO.setUsername(request.getParameter("username"));
      loginDTO.setPassword(request.getParameter("password"));

      var violations = validator.validate(loginDTO);
      if (!violations.isEmpty()) {
        String errorMessage = violations.stream()
                .map(v -> v.getMessage())
                .findFirst()
                .orElse("Error en las credenciales");
        request.getSession().setAttribute("errorMessage", errorMessage);
        response.sendRedirect("/login?error=true");
        return;
      }
    }
    filterChain.doFilter(request, response);
  }

}
