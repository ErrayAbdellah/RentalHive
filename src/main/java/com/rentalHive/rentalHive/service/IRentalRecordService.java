package com.rentalHive.rentalHive.service;

import com.rentalHive.rentalHive.model.entities.RentalRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
public interface IRentalRecordService {
    ResponseEntity record(RentalRecord rentalRecord )throws ParseException;
    ResponseEntity deleteRecord(Long recordId);

}
