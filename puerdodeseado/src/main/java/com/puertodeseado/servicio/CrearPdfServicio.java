package com.puertodeseado.servicio;

import com.puertodeseado.clases.RopaStockGeneral;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.stereotype.Service;


import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.List;
@Service
public class CrearPdfServicio {
  public void crearPdf(OutputStream outputStream, List<RopaStockGeneral> ropaStock) throws FileNotFoundException {

    //String nombreArchivo = "C:/paBLO/stockUniformes.pdf"; // aquí se guarda el archivo

    PdfWriter writer = new PdfWriter(outputStream);
    PdfDocument pdfDocument = new PdfDocument(writer);
    Document document = new Document(pdfDocument, PageSize.A4);

    // Agregar título
    document.add(new Paragraph("stock general UNIFORMES"))
            .setFontSize(18)
            .setTextAlignment(TextAlignment.CENTER);

    // Crear tabla

    Table table = new Table(new float[]{1,1,1,1})
            .useAllAvailableWidth()
            .setAutoLayout()
            .setWidth(UnitValue.createPercentValue(80));


    // Agregar encabezado de tabla

    table.addHeaderCell(new Cell().add(new Paragraph("PRENDA")).setFontSize(15).setBackgroundColor(new DeviceRgb(200, 200, 200)));
    table.addHeaderCell(new Cell().add(new Paragraph("TALLE")).setFontSize(15).setBackgroundColor(new DeviceRgb(200, 200, 200)));
    table.addHeaderCell(new Cell().add(new Paragraph("ESTADO")).setFontSize(15).setBackgroundColor(new DeviceRgb(200, 200, 200)));
    table.addHeaderCell(new Cell().add(new Paragraph("STOCK")).setFontSize(15).setBackgroundColor(new DeviceRgb(200, 200, 200)));

    // Agregar filas de la tabla

    if (ropaStock != null){
      for (RopaStockGeneral rS : ropaStock){
        Cell hola = new Cell()
                .add(new Paragraph(rS.getPrenda()))
                .setFontSize(10)
                .setMinWidth(1)
                .setPadding(5)
                .setWordSpacing(5);


        table.addCell(hola);
        table.addCell(new Cell().add(new Paragraph(rS.getTalle())).setFontSize(10).setMinWidth(1));
        table.addCell(new Cell().add(new Paragraph(rS.getEstado())).setFontSize(10).setMinWidth(1));
        table.addCell(new Cell().add(new Paragraph(String.valueOf(rS.getStock()))).setFontSize(10).setMinWidth(1));

      }
    }else {
      document.add(new Paragraph("No hay prendas disponibles."));
    }




    document.add(table);
    document.close();
  }
}
