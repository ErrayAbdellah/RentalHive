package com.rentalHive.rentalHive.controller;
import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.model.entities.enums.Status;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.rentalHive.rentalHive.repository.IEquipmentRepo;
import com.rentalHive.rentalHive.service.implementations.EquipmentServiceImpl;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {
    private IEquipmentRepo IEquipmentRepo;
    private final EquipmentServiceImpl equipmentService;
    public EquipmentController(EquipmentServiceImpl equipmentService) {
        this.equipmentService = equipmentService;
    }

    @Autowired
    public void EquipementController(IEquipmentRepo IEquipmentRepo) {
        this.IEquipmentRepo = IEquipmentRepo;
    }

    @PostMapping
    public ResponseEntity<String> createEquipement(@Valid @RequestBody EquipmentDTO equipmentDTO)
    {
        Equipment newEquipment = new Equipment();
        BeanUtils.copyProperties(equipmentDTO,newEquipment);
        IEquipmentRepo.save(newEquipment);
        IEquipmentRepo.save(newEquipment);
        IEquipmentRepo.save(newEquipment);
        return  ResponseEntity.ok("Equipement created succesfully");
    }
    @GetMapping("/all")
    public ResponseEntity<List<Equipment>> getAllEquipment() {
        List<Equipment> equipment = IEquipmentRepo.findAll();
        return ResponseEntity.ok(equipment);
    }

    @PutMapping("/{equipmentId}")
    public ResponseEntity<String> updateEquipment(
            @PathVariable Long equipmentId,
            @RequestBody EquipmentDTO equipmentDTO) {

        Optional<Equipment> optionalEquipment = IEquipmentRepo.findById((long) Math.toIntExact(equipmentId));

        if (optionalEquipment.isPresent()) {
            Equipment existingEquipment = optionalEquipment.get();

            existingEquipment.setName(equipmentDTO.getName());
            existingEquipment.setPrice(equipmentDTO.getPrice());
            existingEquipment.setQuantity(equipmentDTO.getQuantity());
            existingEquipment.setStatus(equipmentDTO.getStatus());

            IEquipmentRepo.save(existingEquipment);

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

    @GetMapping("/findByStatus/{status}")
    public ResponseEntity<List<EquipmentDTO>> findEquipmentByStatus(@PathVariable Status status) {
        List<EquipmentDTO> equipmentList = equipmentService.findEquipmentByStatus(status);

        if (!equipmentList.isEmpty()) {
            return new ResponseEntity<>(equipmentList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

