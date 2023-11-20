package com.rentalHive.rentalHive.controller;

import com.rentalHive.rentalHive.model.dto.CustomResponse;
import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.model.entities.enums.Status;
import com.rentalHive.rentalHive.repository.EquipementRepo;
import com.rentalHive.rentalHive.service.implementations.EquipmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/equipment")
public class EquipmentController {

    private EquipementRepo equipementRepo;
    private EquipmentServiceImpl equipmentService;
    @Autowired
    public void EquipementController(EquipementRepo equipementRepo , EquipmentServiceImpl equipmentService) {
        this.equipementRepo = equipementRepo;
        this.equipmentService = equipmentService;
    }

    @PostMapping(consumes = "application/json" )
    @ResponseStatus(value = HttpStatus.CREATED )
    public ResponseEntity<CustomResponse<EquipmentDTO>> addEquipment(@RequestBody EquipmentDTO equipmentDTO){
        try{
            EquipmentDTO equipment= equipmentService.createEquipment(equipmentDTO);
            CustomResponse<EquipmentDTO> response = new CustomResponse<>("Equipment Created successfully", equipment);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            String errorMsg = "Invalid request : "+ e.getMessage();
            CustomResponse<EquipmentDTO> response = new CustomResponse<>(errorMsg, null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            String errorMsg = "Internal Server error : "+ e.getMessage();
            return new ResponseEntity<>(new CustomResponse<>(errorMsg, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

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
