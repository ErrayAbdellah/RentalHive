package com.rentalHive.rentalHive.PdfUtils;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.rentalHive.rentalHive.model.entities.Contrat;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.repository.IEquipmentRepo;
import lombok.Data;
public class PdfGenerator {
    public static void generate(Contrat contract, String filePath) throws DocumentException, IOException {

        Document document = new Document(PageSize.A4);


        PdfWriter.getInstance(document, new FileOutputStream(filePath));


        document.open();


        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTitle.setSize(20);


        Paragraph paragraph1 = new Paragraph("Contract Details", fontTitle);


        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);


        document.add(paragraph1);


        PdfPTable table = new PdfPTable(3);


        table.setWidthPercentage(100f);
        table.setWidths(new int[]{1, 2, 2});
        table.setSpacingBefore(3);


        PdfPCell cell = new PdfPCell();


        cell.setBackgroundColor(CMYKColor.PINK);
        cell.setPadding(3);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.BLACK);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Signature", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);

        // Adding contract data
        table.addCell(String.valueOf(contract.getId()));
        table.addCell(contract.getDescription());

        table.addCell(contract.getUser().getName());

        document.add(table);
        document.close();
    }
}
