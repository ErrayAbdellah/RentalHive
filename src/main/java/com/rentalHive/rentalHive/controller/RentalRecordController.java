package com.rentalHive.rentalHive.controller;

import com.rentalHive.rentalHive.model.dto.RentalRecordDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.model.entities.RentalRecord;
import com.rentalHive.rentalHive.repository.IEquipmentRepo;
import com.rentalHive.rentalHive.repository.IRentalRecordRepo;
import com.rentalHive.rentalHive.service.IRentalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rentalRecord")
public class RentalRecordController {
    private final IEquipmentRepo IEquipmentRepo;
    private final IRentalRecordService rentalRecordService ;

    @GetMapping("/{equipmentId}/rental-history")
    public ResponseEntity<List<RentalRecordDTO>> getEquipmentRentalHistory(@PathVariable Long equipmentId) {
        Optional<Equipment> optionalEquipment = IEquipmentRepo.findById(equipmentId);

        if (optionalEquipment.isPresent()) {
            Equipment equipment = optionalEquipment.get();
            List<RentalRecordDTO> rentalHistory = getRentalHistoryForEquipment(equipment);
            return ResponseEntity.ok(rentalHistory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private List<RentalRecordDTO> getRentalHistoryForEquipment(Equipment equipment) {
        List<RentalRecord> rentalRecords = equipment.getRentalRecords();
        List<RentalRecordDTO> rentalRecordDTOs = new ArrayList<>();

        for (RentalRecord rentalRecord : rentalRecords) {
            RentalRecordDTO rentalRecordDTO = new RentalRecordDTO();
            BeanUtils.copyProperties(rentalRecord, rentalRecordDTO);
            rentalRecordDTOs.add(rentalRecordDTO);
        }
        return rentalRecordDTOs;
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