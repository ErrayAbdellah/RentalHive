package com.rentalHive.rentalHive.controller;

import com.rentalHive.rentalHive.model.dto.RentalRecordDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.model.entities.RentalRecord;
import com.rentalHive.rentalHive.repository.EquipmentRepo;
import com.rentalHive.rentalHive.repository.IRentalRecordRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/equipment")
public class RentalRecordController {
    private final EquipmentRepo equipmentRepo;
    private final IRentalRecordRepo rentalRecordRepo;

    @GetMapping("/{equipmentId}/rental-history")
    public ResponseEntity<List<RentalRecordDTO>> getEquipmentRentalHistory(@PathVariable Long equipmentId) {
        Optional<Equipment> optionalEquipment = equipmentRepo.findById((long) Math.toIntExact(equipmentId));

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
//        private final IRentalRecordService rentalRecordService ;

//        @PostMapping("/save")
//        public ResponseEntity record () throws ParseException {
////        User user=new User();
////        Equipment equipment= new Equipment();
//
//            return rentalRecordRepo.record(1, 1);
//
//
//        }

}