package com.rentalHive.rentalHive.controller;

import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.repository.EquipementRepo;
import com.rentalHive.rentalHive.service.IEquipmentService;
import com.rentalHive.rentalHive.service.implementations.EquipmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {
    private EquipementRepo equipementRepo;
    private final EquipmentServiceImpl equipmentService;
    public EquipmentController(EquipmentServiceImpl equipmentService) {
        this.equipmentService = equipmentService;
    }

    @Autowired
    public void EquipementController(EquipementRepo equipementRepo) {
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

    @GetMapping("/findByName/{name}")
    public ResponseEntity<EquipmentDTO> findEquipmentByName(@PathVariable String name) {
        Optional<EquipmentDTO> equipmentDTOOptional = equipmentService.findEquipmentByName(name);

        return equipmentDTOOptional
                .map(equipmentDTO -> new ResponseEntity<>(equipmentDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}