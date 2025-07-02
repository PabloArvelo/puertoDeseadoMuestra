package com.puertodeseado.controladores.anticiporetorno;


import com.docraptor.*;
import com.docraptor.Doc.DocumentTypeEnum;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/GenerarPDFs")
public class GenerarPDFRecibosControlador {



    @Value("${api.key}")
    private String apiKey;  // variable para la key de la api

    @Value("${api.test}")
    private Boolean apiTest;  // variable para modo test o producción de la api



    @PostMapping
    public ResponseEntity<Resource> generatePdfs(@RequestBody Map<String, List<String>> request) throws IOException {
        List<String> recibos = request.get("recibos");

        LocalDateTime asd = LocalDateTime.now();
        System.out.println(asd + " hola desde generatePdfs cuando entra");


        ByteArrayOutputStream zipOutputStream = new ByteArrayOutputStream();
        try (ZipOutputStream zos = new ZipOutputStream(zipOutputStream)) {
            int counter = 1;
            for (String htmlContent : recibos) {

                // Parseo el HTML con Jsoup
                Document doc = Jsoup.parse(htmlContent);

                //String nombreAsoc = doc.select("div.fila2.parapdf#apelligoYNombre").text();
                String nombreAsoc = doc.select("div.parapdf").text();


                // Llamar a DocRaptor para convertir HTML a PDF
                byte[] pdfBytes = convertHtmlToPdf(htmlContent);


                // Agregar el PDF al archivo ZIP
                ZipEntry entry = new ZipEntry(nombreAsoc+ ".pdf");
                zos.putNextEntry(entry);
                zos.write(pdfBytes);
                zos.closeEntry();
            }
        }

        // Configurar la respuesta como un archivo ZIP
        ByteArrayResource resource = new ByteArrayResource(zipOutputStream.toByteArray());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=recibos.zip");

        LocalDateTime qwe = LocalDateTime.now();
        System.out.println(qwe+ " hola desde generatePdfs cuando sale");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(zipOutputStream.size())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    private byte[] convertHtmlToPdf(String htmlContent) {


        // Crear cliente de DocRaptor
        DocApi docApi = new DocApi();
        ApiClient client = docApi.getApiClient();
        client.setUsername(apiKey);  // Configurar la clave API

        // Crear un objeto Document para los parámetros de la solicitud
        Doc document = new Doc();

        document.setDocumentContent(htmlContent);  // Establecer el contenido HTML
        document.setName("recibo.pdf");  // Nombre del archivo PDF
        document.setTest(apiTest);  // Cambiar a false para producción
        document.setDocumentType(DocumentTypeEnum.PDF);  // Tipo de documento (PDF)

        // el navegador por defecto, usa screen para visualizar y docraptor, print
        // seteo screen para que se vea como en el navegador
        PrinceOptions princeOptions = new PrinceOptions();
        princeOptions.setMedia("screen");
        document.setPrinceOptions(princeOptions);


        try {
            // Llamar a la API para generar el documento PDF
            byte[] pdfBytes = docApi.createDoc(document);

            LocalDateTime zxc = LocalDateTime.now();
            System.out.println(zxc + " hola desde convertHtmlToPdf cuando obtuvo el archivo");

            // Retornar el archivo PDF generado
            return pdfBytes;
        } catch (ApiException e) {
            // Manejar cualquier excepción de la API
            throw new RuntimeException("Error al contactar con la API de DocRaptor: " + e.getMessage());
        }
    }

}



