package com.rentalHive.rentalHive.controller;

import com.rentalHive.rentalHive.service.IRentalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/record")
public class RentalRecordController {


        private final IRentalRecordService rentalRecordService ;

        @PostMapping("/save")
        public ResponseEntity record() throws ParseException {
//        User user=new User();
//        Equipment equipment= new Equipment();

            return rentalRecordService.record(1,1);
        }

}
