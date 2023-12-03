package com.rentalHive.rentalHive.controller;

import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.Contrat;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.model.entities.Image;
import com.rentalHive.rentalHive.model.entities.enums.Category;
import com.rentalHive.rentalHive.repository.IEquipmentRepo;
import com.rentalHive.rentalHive.service.IEquipmentService;
import com.rentalHive.rentalHive.utils.PdfGenerator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/equipment")
public class EquipmentController {

    @Qualifier("equipmentServiceImpl")
    private final IEquipmentService equipmentService;

    private final IEquipmentRepo equipmentRepo;

    @PostMapping
    public ResponseEntity createEquipment(
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("category") Category category,
            @RequestParam("image") MultipartFile image
            ) throws SQLException, IOException {
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        equipmentDTO.setName(name);
        equipmentDTO.setPrice(price);
        equipmentDTO.setCategory(category);
        System.out.println(category);
        System.out.println(image);
        Equipment.ToEquipment(equipmentDTO);

        Optional<Equipment> equipment = equipmentRepo.findById(1L);
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD_HH-MM-SS");
        String currentDateTime = dateFormat.format(new Date());
        String filePath = "PDFs/" + currentDateTime + ".pdf";
        Contrat contrat = new Contrat(1L,"ee",null,null);


        PdfGenerator.generate(equipment.get(),filePath);
        return  equipmentService.createEquipment(equipmentDTO,image);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Equipment>> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }

    @PutMapping("/{equipmentId}")
    public ResponseEntity<String> updateEquipment(
            @PathVariable Long equipmentId,
            @RequestBody EquipmentDTO equipmentDTO) {

        return equipmentService.updateEquipment(equipmentId,equipmentDTO);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<EquipmentDTO> findEquipmentByName(@PathVariable String name) {
        return equipmentService.findEquipmentByName(name);
    }

    @GetMapping("/findByStatus/{status}")
    public ResponseEntity<List<EquipmentDTO>> findEquipmentByStatus(@PathVariable Category type) {
       return equipmentService.findEquipmentByType(type);
    }

}

