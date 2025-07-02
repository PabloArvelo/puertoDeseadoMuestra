package com.puertodeseado.controladores.seguridad;

import com.puertodeseado.seguridad.RecuperoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PasswordResetControlador {

  @Autowired
  private RecuperoServicio recuperoServicio;

  @GetMapping("/recupero")
  public String showRecoverForm() {
    return "seguridad/recuperoCuenta";
  }

  @PostMapping("/recover-account")
  @ResponseBody
  public String recoverAccount(@RequestParam String email) {
    recuperoServicio.requestRecovery(email);
    String maskedEmail = maskEmail(email);
    return "Si el email existe, se ha enviado un mensaje a " + maskedEmail;
  }

  @GetMapping("/reset-password")
  public String showResetForm(@RequestParam("token") String token, Model model) {
    model.addAttribute("token", token);
    return "seguridad/resetPassword"; // Esto renderiza la vista
  }

  @PostMapping("/reset-password")
  @ResponseBody
  public String resetPassword(@RequestParam String token, @RequestParam String newPassword) {
    try {
      recuperoServicio.resetPassword(token, newPassword);
      return "Contraseña actualizada exitosamente";
    } catch (IllegalArgumentException e) {
      return e.getMessage(); // Devuelve el error como texto plano
    }
  }

  private String maskEmail(String email) {
    String[] parts = email.split("@");
    String localPart = parts[0];
    String domain = parts[1];
    if (localPart.length() <= 2) {
      return localPart.charAt(0) + "****@" + domain;
    }
    return localPart.substring(0, 2) + "****@" + domain;
  }
}
