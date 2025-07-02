package com.puertodeseado.servicio;

import com.puertodeseado.EntidadesDTO.RespuestaDTO;
import com.puertodeseado.EntidadesDTO.anticipoderetorno.AsociadosListaLiquidacionDTO;
import com.puertodeseado.entidades.AsociadosMain;
import com.puertodeseado.repositorio.AsociadosMainRepositorio;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class CrearExcelFileServicio {
    @Autowired
    private AsociadosMainRepositorio asociadosMainRepositorio;

    public ResponseEntity<byte[]> listadoAsociadosLiquidacionWeb(){


        try {

            List<Object[]> miLista = new ArrayList<>();

            // GUARDO LO QUE TRAIGO DE LA DB
            miLista = asociadosMainRepositorio.listaLiquidacion();

            List<AsociadosListaLiquidacionDTO> asociadosLiquidacion = new ArrayList<>();

            for (Object[] fila : miLista) {
                AsociadosListaLiquidacionDTO asociado = new AsociadosListaLiquidacionDTO();
                asociado.setId((String) fila[0]);
                asociado.setApellido((String) fila[1]);
                asociado.setNombre((String) fila[2]);
                asociado.setCuil((Long) fila[3]);
                asociadosLiquidacion.add(asociado);
            }

            // Crear un workbook para el archivo Excel
            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Liquidacion");   // aca completar en e nombre de la hoja el período a liquidar

            // Crear una fila de encabezado
            Row encabezado = sheet.createRow(0);
            encabezado.createCell(0).setCellValue("idAsociado");
            encabezado.createCell(1).setCellValue("apellidos");
            encabezado.createCell(2).setCellValue("nombres");
            encabezado.createCell(3).setCellValue("cuil");
            encabezado.createCell(4).setCellValue("horas1");
            encabezado.createCell(5).setCellValue("minutos1");
            encabezado.createCell(6).setCellValue("valorHora1");
            encabezado.createCell(7).setCellValue("objetivo1");
            encabezado.createCell(8).setCellValue("horas2");
            encabezado.createCell(9).setCellValue("minutos2");
            encabezado.createCell(10).setCellValue("valorHora2");
            encabezado.createCell(11).setCellValue("objetivo2");
            encabezado.createCell(12).setCellValue("horas3");
            encabezado.createCell(13).setCellValue("minutos3");
            encabezado.createCell(14).setCellValue("valorHora3");
            encabezado.createCell(15).setCellValue("objetivo3");
            encabezado.createCell(16).setCellValue("horas4");
            encabezado.createCell(17).setCellValue("minutos4");
            encabezado.createCell(18).setCellValue("valorHora4");
            encabezado.createCell(19).setCellValue("objetivo4");
            encabezado.createCell(20).setCellValue("horas5");
            encabezado.createCell(21).setCellValue("minutos5");
            encabezado.createCell(22).setCellValue("valorHora5");
            encabezado.createCell(23).setCellValue("objetivo5");
            encabezado.createCell(24).setCellValue("horas6");
            encabezado.createCell(25).setCellValue("minutos6");
            encabezado.createCell(26).setCellValue("valorHora6");
            encabezado.createCell(27).setCellValue("objetivo6");
            encabezado.createCell(28).setCellValue("horas7");
            encabezado.createCell(29).setCellValue("minutos7");
            encabezado.createCell(30).setCellValue("valorHora7");
            encabezado.createCell(31).setCellValue("objetivo7");
            encabezado.createCell(32).setCellValue("horas8");
            encabezado.createCell(33).setCellValue("minutos8");
            encabezado.createCell(34).setCellValue("valorHora8");
            encabezado.createCell(35).setCellValue("objetivo8");
            encabezado.createCell(36).setCellValue("horas9");
            encabezado.createCell(37).setCellValue("minutos9");
            encabezado.createCell(38).setCellValue("valorHora9");
            encabezado.createCell(39).setCellValue("objetivo9");
            encabezado.createCell(40).setCellValue("horas10");
            encabezado.createCell(41).setCellValue("minutos10");
            encabezado.createCell(42).setCellValue("valorHora10");
            encabezado.createCell(43).setCellValue("objetivo10");
            encabezado.createCell(44).setCellValue("presentismo");
            encabezado.createCell(45).setCellValue("nocturnidad");
            encabezado.createCell(46).setCellValue("nocturnidadEventual");
            encabezado.createCell(47).setCellValue("bonificacionFeriados");
            encabezado.createCell(48).setCellValue("reconocimientoHoras");
            encabezado.createCell(49).setCellValue("carpetaMedica");
            encabezado.createCell(50).setCellValue("hsVacaciones");
            encabezado.createCell(51).setCellValue("horasAdeudadas");
            encabezado.createCell(52).setCellValue("licienciaMaternidad");
            encabezado.createCell(53).setCellValue("horasPractica");
            encabezado.createCell(54).setCellValue("reintegroCuotasSociales");
            encabezado.createCell(55).setCellValue("seguroAccPer_Vida");
            encabezado.createCell(56).setCellValue("retenMonotriPer");
            encabezado.createCell(57).setCellValue("seguroVidaOblig");
            encabezado.createCell(58).setCellValue("costoHabilitacion");
            encabezado.createCell(59).setCellValue("aptoPsiFi");
            encabezado.createCell(60).setCellValue("credisolCS");
            encabezado.createCell(61).setCellValue("credisolCC");
            encabezado.createCell(62).setCellValue("creditoGUPAServicioSAS");
            encabezado.createCell(63).setCellValue("almacen");
            encabezado.createCell(64).setCellValue("embargoJudicial");
            encabezado.createCell(65).setCellValue("depositoExceso");
            encabezado.createCell(66).setCellValue("descuentoRoturaPerdida");
            encabezado.createCell(67).setCellValue("calzado");
            encabezado.createCell(68).setCellValue("adherenteMonotributo");


            // Crear el estilo para las celdas de encabezado
            CellStyle encabezadoEstilo = workbook.createCellStyle();
            Font encabezadoFuente = workbook.createFont();
            encabezadoFuente.setBold(true);
            encabezadoEstilo.setFont(encabezadoFuente);


            // Aplicar el estilo a las celdas de encabezado
            for (int i = 0; i < 69; i++) {
                encabezado.getCell(i).setCellStyle(encabezadoEstilo);
            }


            // Crear un estilo de celda
            XSSFCellStyle style = workbook.createCellStyle();
            // Crear un color RGB personalizado
            byte[] rgb = new byte[]{(byte) 191, (byte) 191, (byte) 191};
            XSSFColor colorGris = new XSSFColor(rgb, null);

            style.setFillForegroundColor(colorGris);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            // Escribir los datos de la lista en el archivo

            int numerFila = 1;
            for (AsociadosListaLiquidacionDTO listado : asociadosLiquidacion) {
                Row fila = sheet.createRow(numerFila++);

                Cell celda0 = fila.createCell(0);
                Cell celda1 = fila.createCell(1);
                Cell celda2 = fila.createCell(2);
                Cell celda3 = fila.createCell(3);

                celda0.setCellValue(listado.getId());
                celda1.setCellValue(listado.getApellido());
                celda2.setCellValue(listado.getNombre());
                celda3.setCellValue(listado.getCuil());

                celda0.setCellStyle(style);
                celda1.setCellStyle(style);
                celda2.setCellStyle(style);
                celda3.setCellStyle(style);
            }
            for (int i = 0; i < 69; i++) {
                sheet.autoSizeColumn(i);
                int currentWidth = sheet.getColumnWidth(i);
                sheet.setColumnWidth(i, currentWidth + 512);
            }


            // Escribir en memoria
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            workbook.write(outputStream);
            workbook.close();

            // Configurar la respuesta HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=Listado_Liquidacion.xlsx");

            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);


        }catch (IOException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
