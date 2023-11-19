package com.rentalHive.rentalHive.controller;

import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.repository.EquipementRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/equipment")
public class EquipmentController {

    private EquipementRepo equipementRepo;

    @Autowired
    public void EquipementController(EquipementRepo equipementRepo) {
        this.equipementRepo = equipementRepo;
    }
    @PostMapping
    public ResponseEntity<String> createEquipement(@Valid @RequestBody EquipmentDTO equipmentDTO)
    {
        Equipment newEquipment = new Equipment();
        BeanUtils.copyProperties(equipmentDTO,newEquipment);
        equipementRepo.save(newEquipment);
        return  ResponseEntity.ok("Equipement created succesfully");
    }
    @GetMapping("/all")
    public ResponseEntity<List<Equipment>> getAllEquipment() {
        List<Equipment> equipment = equipementRepo.findAll();
        return ResponseEntity.ok(equipment);
    }
    @PutMapping("/{equipmentId}")
    public ResponseEntity<String> updateEquipment(
            @PathVariable Long equipmentId,
            @RequestBody EquipmentDTO equipmentDTO) {

        Optional<Equipment> optionalEquipment = equipementRepo.findById(Math.toIntExact(equipmentId));

        if (optionalEquipment.isPresent()) {
            Equipment existingEquipment = optionalEquipment.get();
            BeanUtils.copyProperties(equipmentDTO, existingEquipment);
            equipementRepo.save(existingEquipment);

            return ResponseEntity.ok("Equipment updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
