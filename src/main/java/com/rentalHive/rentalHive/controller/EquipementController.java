package com.rentalHive.rentalHive.controller;

import com.rentalHive.rentalHive.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.repository.EquipementRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/equipment")
public class EquipementController {

    private final EquipementRepo equipementRepo;

    @Autowired
    public EquipementController(EquipementRepo equipementRepo) {
        this.equipementRepo = equipementRepo;
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
