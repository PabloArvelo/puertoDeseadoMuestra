package com.puertodeseado.EntidadesDTO.seguridad;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginDTO {

  @NotBlank(message = "El correo electrónico es obligatorio")
  @Email(message = "Debe ser un correo electrónico válido")
  @Size(max = 50, message = "El correo electrónico no puede exceder los 50 caracteres")
  private String username;

  @NotBlank(message = "La contraseña es obligatoria")
  @Size(min = 6, max = 50, message = "La contraseña debe tener entre 6 y 50 caracteres")
  private String password;

  public LoginDTO() {
  }

  public LoginDTO(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
