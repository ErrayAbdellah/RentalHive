package com.rentalHive.rentalHive.controller;

import com.rentalHive.rentalHive.model.dto.RentalRecordDTO;
import com.rentalHive.rentalHive.service.IRentalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rentalRecord")
public class RentalRecordController {

    private final IRentalRecordService rentalRecordService ;

    @GetMapping("/{equipmentId}/rental-history")
    public ResponseEntity<List<RentalRecordDTO>> getEquipmentRentalHistory(@PathVariable long equipmentId) {
        return rentalRecordService.getEquipmentRentalHistory(equipmentId);
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