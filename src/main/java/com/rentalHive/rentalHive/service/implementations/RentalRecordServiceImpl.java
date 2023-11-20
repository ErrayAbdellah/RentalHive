package com.rentalHive.rentalHive.service.implementations;

import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.model.entities.RentalRecord;
import com.rentalHive.rentalHive.model.entities.User;
import com.rentalHive.rentalHive.repository.EquipmentRepo;
import com.rentalHive.rentalHive.repository.IRentalRecordRepo;
import com.rentalHive.rentalHive.repository.IUserRepo;
import com.rentalHive.rentalHive.service.IRentalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RentalRecordServiceImpl implements IRentalRecordService {
    private final IRentalRecordRepo rentalRecordRepo ;
    private final IUserRepo userRepo ;
    private final EquipmentRepo equipmentRepo ;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");


    @Override
    public ResponseEntity record(long  userId, long equipmentId) throws ParseException {
        Optional<User> user = userRepo.findById(userId);
        Optional<Equipment> equipment = equipmentRepo.findById(equipmentId);
        RentalRecord record = new RentalRecord();
        if (!equipment.isPresent()) {
            return ResponseEntity.badRequest().body("equipment is not present");
        } else if (!user.isPresent()) {
            return ResponseEntity.badRequest().body("user is not present");
        }
        record.setEquipment(equipment.get());
        record.setUser(user.get());

        record.setReservationDate(dateFormat.parse("02/22/2023"));
        record.setReturnDate(dateFormat.parse("02/30/2023"));
        System.out.println(record);
//        rentalRecordRepo.save(record);
        return ResponseEntity.ok("record is successfully");
    }
}
