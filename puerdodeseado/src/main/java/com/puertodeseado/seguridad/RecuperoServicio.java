package com.puertodeseado.seguridad;

import com.puertodeseado.entidades.seguridad.Usuario;
import com.puertodeseado.repositorio.seguridad.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecuperoServicio {

  @Autowired
  private UsuarioRepositorio usuarioRepositorio;

  @Autowired
  private JavaMailSender mailSender;

  @Autowired
  private PasswordEncoder passwordEncoder;

  // Solicitar recuperación (usuario y/o contraseña)
  public void requestRecovery(String email) {
    Optional<Usuario> usuarioOpt = usuarioRepositorio.findUserEntityByUserName(email);
    if (usuarioOpt.isPresent()) {
      Usuario usuario = usuarioOpt.get();
      String token = UUID.randomUUID().toString();
      usuario.setResetToken(token);
      usuario.setResetTokenExpiry(Date.from(Instant.now().plus(10, ChronoUnit.MINUTES)));
      usuarioRepositorio.save(usuario);

      sendRecoveryEmail(usuario.getUserName(), token);
    }
    // No lanzar excepción si no existe el email para evitar revelar información
  }

  // Enviar email de recuperación
  private void sendRecoveryEmail(String email, String token) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(email);
    message.setSubject("Recuperación de cuenta");
    message.setText("Tu nombre de usuario es: " + maskEmail(email) + "\n" +
            "Para resetear tu contraseña, haz click aquí: " +
            "http://localhost:8080/reset-password?token=" + token);
    mailSender.send(message);
  }

  // Ofuscar email
  private String maskEmail(String email) {
    String[] parts = email.split("@");
    String localPart = parts[0];
    String domain = parts[1];
    if (localPart.length() <= 2) {
      return localPart.charAt(0) + "****@" + domain;
    }
    return localPart.substring(0, 2) + "****@" + domain;
  }

  // Resetear contraseña (como antes)
  public void resetPassword(String token, String newPassword) {
    Usuario usuario = usuarioRepositorio.findByResetToken(token)
            .orElseThrow(() -> new IllegalArgumentException("Token inválido"));
    if (usuario.getResetTokenExpiry().before(new Date())) {
      throw new IllegalArgumentException("Token expirado");
    }
    usuario.setPassword(passwordEncoder.encode(newPassword));
    usuario.setResetToken(null);
    usuario.setResetTokenExpiry(null);
    usuarioRepositorio.save(usuario);
  }
}
