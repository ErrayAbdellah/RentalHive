package com.rentalHive.rentalHive.PdfUtils;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.rentalHive.rentalHive.model.ConditionDTO;
import com.rentalHive.rentalHive.model.entities.Contrat;
import com.rentalHive.rentalHive.model.entities.Equipment;
public class PdfGenerator {

    public static void generate(Contrat contract, List<ConditionDTO> conditionDTOList, String filePath) throws DocumentException, IOException {

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, new FileOutputStream(filePath));


        document.open();


        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTitle.setSize(15);


        Paragraph paragraph1 = new Paragraph("Contract Details", fontTitle);


        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);


        document.add(paragraph1);


        PdfPTable table = new PdfPTable(6);


        table.setWidthPercentage(100f);
        table.setWidths(new int[]{1, 2, 2, 3, 3, 2});
        table.setSpacingBefore(3);


        PdfPCell cell = new PdfPCell();


        cell.setBackgroundColor(CMYKColor.PINK);
        cell.setPadding(3);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.BLACK);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Reservation Date", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Return Date", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Equipment Names", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Conditions", font)); // New column for Contract
        table.addCell(cell);
        cell.setPhrase(new Phrase("Price", font)); // New column for Contract
        table.addCell(cell);

        String equipmentNames = "";
        for (Equipment equipment : contract.getDevis().getDemande().getEquipment()) {
            equipmentNames += "* " + equipment.getName() + "\n";
        }
        String contractConditions = "";
        for (ConditionDTO condition : conditionDTOList){
            contractConditions += "* "+condition.getBody()+"\n";
        }
        // Adding contract data
        table.addCell(String.valueOf(contract.getId()));
        table.addCell(String.valueOf(contract.getDevis().getDemande().getDemande_date()).substring(0, 11));
        table.addCell(String.valueOf(contract.getDevis().getDemande().getDate_retour()).substring(0, 11));
        table.addCell(equipmentNames);
        table.addCell(contractConditions); // Add your contract data here
        table.addCell(contract.getDevis().getTotalPrix()+" MAD"); // Add your contract data here

        document.add(table);
        document.close();
    }
}