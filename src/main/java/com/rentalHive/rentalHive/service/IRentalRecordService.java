package com.rentalHive.rentalHive.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public interface IRentalRecordService {
    ResponseEntity record(long userId , long equipmentId )throws ParseException;

}
