package com.rentalHive.rentalHive.controller;


import com.rentalHive.rentalHive.model.dto.RentalRecordDTO;
import com.rentalHive.rentalHive.model.entities.Contrat;
import com.rentalHive.rentalHive.service.IRentalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rentalRecord")
public class RentalRecordController {

    @Qualifier("rentalRecordServiceImpl")
    private final IRentalRecordService rentalRecordService ;

    @GetMapping(value = "/pdf")
    public void pdf() throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD_HH-MM-SS");
        String currentDateTime = dateFormat.format(new Date());
        String filePath = "PDFs/" + currentDateTime + ".pdf";
//        Contrat contrat = new Contrat(1L,"ee",null,null);
//        System.out.println(contrat);
//        PdfGenerator.generate(contrat, filePath);
//        generator.generate(contrat, filePath);
    }
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws IOException {
        // Logic to load the file as a Resource (e.g., from the file system, database, etc.)
        // Replace this with your actual implementation
        Resource resource = loadFileAsResource(fileName);

        // Set content disposition to trigger download
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
    private Resource loadFileAsResource(String fileName) throws IOException {
        try {
            Path filePath = Paths.get("PDFs" + fileName);
            Resource resource = new FileSystemResource(filePath.toFile());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("File not found or not readable: " + fileName);
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not load the file: " + fileName, e);
        }
    }
    @GetMapping("/{equipmentId}/rental-history")
    public ResponseEntity<List<RentalRecordDTO>> getEquipmentRentalHistory(@PathVariable long equipmentId) {
        return rentalRecordService.getEquipmentRentalHistory(equipmentId);
    }

    @PostMapping("/save")
    public ResponseEntity record (@RequestBody RentalRecordDTO recordDTO){
        return rentalRecordService.record(recordDTO);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity deleteRecord(@PathVariable long id){
        return rentalRecordService.deleteRecord(id);
    }

}