package com.puertodeseado.servicioNotificacionesEmail;

import com.puertodeseado.entidades.AsociadosMain;
import com.puertodeseado.repositorio.AsociadosMainRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class VencimientosHabilitaciones {
  @Autowired
  private JavaMailSender javaMailSender;
  @Autowired
  private AsociadosMainRepositorio asociadosMainRepositorio;


  // esta es una tarea programada para que todos los días a las 6.00 am envíe mails informado si faltan
  // 45 días, 20 días 7 días para el vencimiento de la habilitación o cuando la habilitación ya venció (el mismo día de la habilitación)

  //@Scheduled(fixedRate = 60000) // para pruebas cada 60 segundos.
  @Scheduled(cron = "0 0 6 * * ?") // todos los días a las 6.00am
  public void enviarmailAgrupado() {

    LocalDate fechaActual = LocalDate.now();

    // guardo todos los asociiados en una lista
    List<AsociadosMain> listaAsociadosHabilitados = new ArrayList<>();

    // obtengo una lista con todos los asociados quue tienen habilitación
    listaAsociadosHabilitados = asociadosMainRepositorio.asociadosConHabilitacion();

    // Creo listas que agrupan los avisos para los asosciados con el mismo vencimiento
    List<AsociadosMain> listaPrimAviso = new ArrayList<>();
    List<AsociadosMain> listaSegAviso = new ArrayList<>();
    List<AsociadosMain> listaUltAviso = new ArrayList<>();
    List<AsociadosMain> listaVencido = new ArrayList<>();

    String apellido = "";
    String nombre = "";
    Integer diasParaVencimiento = null;
    SimpleMailMessage mensaje = new SimpleMailMessage();

    for (int i = 0; i < listaAsociadosHabilitados.size(); i++) {

      AsociadosMain asociado = listaAsociadosHabilitados.get(i);

      // convierto la fecha de habilitación que está en la DDBB a LocalDate
      // para poder calcular la diferencia de 45 días
      LocalDate fechaInicioHabil = listaAsociadosHabilitados.get(i).getHabilitacion()
              .toInstant()
              .atZone(ZoneId.systemDefault())
              .toLocalDate();

      apellido = listaAsociadosHabilitados.get(i).getApellido();
      nombre = listaAsociadosHabilitados.get(i).getNombre();


      // calculo un año desde la fecha de habilitacion (le sumo un año)
      LocalDate fechaVenceHabilitacion = fechaInicioHabil.plusYears(1);


      // calfulo la diferencia de días entre la fecha actual y la fecha de vencimiento de la habilitación
      diasParaVencimiento = Math.toIntExact(ChronoUnit.DAYS.between(fechaActual, fechaVenceHabilitacion));

      if (diasParaVencimiento == 45) {
        listaPrimAviso.add(asociado);
      }
      if (diasParaVencimiento == 20) {
        listaSegAviso.add(asociado);
      }
      if (diasParaVencimiento == 7) {
        listaUltAviso.add(asociado);
      }
      if (diasParaVencimiento == 0) {
        listaVencido.add(asociado);
      }

    }


    if (!listaPrimAviso.isEmpty()) {

      String listaNombresPrimAviso = "";

      // acá acrupo todos los nombres y cuils en un solo string
      for (int i = 0; i < listaPrimAviso.size(); i++) {

        String encuentra = " - "+ listaPrimAviso.get(i).getNombre() +
                " " +
                listaPrimAviso.get(i).getApellido() +
                " " +
                listaPrimAviso.get(i).getCuil();

        listaNombresPrimAviso = listaNombresPrimAviso + "\n" + encuentra;
      }

      LocalDate vence = fechaActual.plusDays(45);
      mailNotificacion("consejopuertodeseado@gmail.com",
              "PRIMER AVISO VENCIMIENTO HABILITACIÓN",
              listaNombresPrimAviso,
              vence,
              45);
    }

    if (!listaSegAviso.isEmpty()){

      String listaNombresSegAviso = "";

      for (int i = 0; i < listaSegAviso.size(); i++) {

        String encuentra = " - "+ listaSegAviso.get(i).getNombre() +
                " " +
                listaSegAviso.get(i).getApellido() +
                " " +
                listaSegAviso.get(i).getCuil();

        listaNombresSegAviso = listaNombresSegAviso + "\n" + encuentra;
      }

      LocalDate vence = fechaActual.plusDays(20);
      mailNotificacion("consejopuertodeseado@gmail.com",
              "SEGUNDO AVISO VENCIMIENTO HABILITACIÓN",
              listaNombresSegAviso,
              vence,
              20);

    }

    if (!listaUltAviso.isEmpty()){

      String listaNombresUltmAviso = "";

      for (int i = 0; i < listaUltAviso.size(); i++) {
        String encuentra = " - "+ listaUltAviso.get(i).getNombre() +
                " " +
                listaUltAviso.get(i).getApellido() +
                " " +
                listaUltAviso.get(i).getCuil();

        listaNombresUltmAviso = listaNombresUltmAviso + "\n" + encuentra;
      }

      LocalDate vence = fechaActual.plusDays(7);
      mailNotificacion("consejopuertodeseado@gmail.com",
              "ULTIMO AVISO VENCIMIENTO HABILITACIÓN",
              listaNombresUltmAviso,
              vence,
              7);
    }

    if (!listaVencido.isEmpty()){

      String listaNombresVencido = "";

      for (int i = 0; i < listaVencido.size(); i++) {

        String encuentra = " - "+ listaVencido.get(i).getNombre() +
                " " +
                listaVencido.get(i).getApellido() +
                " " +
                listaVencido.get(i).getCuil();
        listaNombresVencido = listaNombresVencido + "\n" + encuentra;
      }

      LocalDate vence = fechaActual.plusDays(0);
      mailNotificacionVencio("consejopuertodeseado@gmail.com",
              "HABILITACIONES VENCIDAS",
              listaNombresVencido,
              vence);
    }

  }


  // este método es invocado para enviar mails cuando aun no ha vencido la habilitación del asociado (45, 20 y 7 días antes)
  public void mailNotificacion(String destinatario, String asunto, String listaAsociados, LocalDate fechaVence, Integer cantDias) {

    SimpleMailMessage mensaje = new SimpleMailMessage();
    mensaje.setTo(destinatario);
    mensaje.setSubject(asunto);
    mensaje.setText("La habilitación de el/los asociado/s:\n" + listaAsociados + "\n\nvence el día " + fechaVence +
            ".\n\nFaltan " + cantDias + " días para el vencimiento." +
            "\n\nNO RESPONDER A ESTE CORREO.");

    mensaje.setFrom("notificaciones.puertodeseado@gmail.com");

    javaMailSender.send(mensaje);
    System.out.println("correo programado enviado " + cantDias);
  }

  // este método es invocado para enviar mails cuando ya venció habilitación del asociado (el mismo día que vence)
  public void mailNotificacionVencio(String destinatario, String asunto, String listaAsociados, LocalDate fechaVence) {

    SimpleMailMessage mensaje = new SimpleMailMessage();
    mensaje.setTo(destinatario);
    mensaje.setSubject(asunto);
    mensaje.setText("La habilitación de el/los asociado/s:\n" + listaAsociados + "\n\nvenció el día el día de hoy (" + fechaVence + ")" +
            ".\n\nRenovar la habilitación." +
            "\n\nNO RESPONDER A ESTE CORREO.");


    mensaje.setFrom("notificaciones.puertodeseado@gmail.com");

    javaMailSender.send(mensaje);
    System.out.println("correo programado enviado " + 0);
  }

}
