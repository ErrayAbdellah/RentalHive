package com.rentalHive.rentalHive.service;

import com.rentalHive.rentalHive.model.dto.RentalRecordDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRentalRecordService {
    ResponseEntity record(RentalRecordDTO recordDTO );
    ResponseEntity deleteRecord(Long recordId);

    ResponseEntity<List<RentalRecordDTO>> getEquipmentRentalHistory(long equipmentId);
}
