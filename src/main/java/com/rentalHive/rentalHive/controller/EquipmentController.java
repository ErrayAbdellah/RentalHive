package com.rentalHive.rentalHive.controller;
import com.rentalHive.rentalHive.model.dto.CustomResponse;
import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.model.entities.enums.Status;
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
    private final EquipmentServiceImpl equipmentService;
    public EquipmentController(EquipmentServiceImpl equipmentService) {
        this.equipmentService = equipmentService;
    }

//    @Autowired
//    public void EquipementController(IEquipmentRepo equipmentRepo) {
//        this.equipmentRepo = equipmentRepo;
//    }

    @PostMapping(consumes = "application/json" )
    @ResponseStatus(value = HttpStatus.CREATED )
    public ResponseEntity<CustomResponse<EquipmentDTO>> addEquipment(@RequestBody EquipmentDTO equipmentDTO){
        try{
            EquipmentDTO equipment= equipmentService.createEquipment(equipmentDTO);
            System.out.println("created equipment from controller : "+equipment);
            CustomResponse<EquipmentDTO> response = new CustomResponse<>("Equipment Created successfully", equipment);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            String errorMsg = "Invalid request : "+ e.getMessage();
            CustomResponse<EquipmentDTO> response = new CustomResponse<>(errorMsg, null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            String errorMsg = "Internal Server error : " + e.getMessage();
            CustomResponse<EquipmentDTO> response = new CustomResponse<>(errorMsg, null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Equipment>> getAllEquipment() {
        return equipmentService.getAllEquipment() ;
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateEquipment(@RequestBody EquipmentDTO equipmentDTO) {
       return equipmentService.updateEquipment(equipmentDTO);
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

