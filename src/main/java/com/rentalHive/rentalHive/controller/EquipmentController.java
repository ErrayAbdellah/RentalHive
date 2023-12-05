package com.rentalHive.rentalHive.controller;
import com.rentalHive.rentalHive.enums.Category;
import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.service.IEquipmentService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    private final IEquipmentService equipmentService;

    public EquipmentController(IEquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping
    public ResponseEntity createEquipment(@Valid @RequestBody EquipmentDTO equipmentDTO){
        return  equipmentService.createEquipment(equipmentDTO);
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

