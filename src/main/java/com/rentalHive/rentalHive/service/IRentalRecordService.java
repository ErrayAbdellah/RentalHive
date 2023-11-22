package com.rentalHive.rentalHive.service;

import com.rentalHive.rentalHive.model.dto.RentalRecordDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface IRentalRecordService {
    ResponseEntity record(RentalRecordDTO recordDTO );
    ResponseEntity deleteRecord(Long recordId);

}
