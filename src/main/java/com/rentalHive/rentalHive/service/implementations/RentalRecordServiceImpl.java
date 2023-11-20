package com.rentalHive.rentalHive.service.implementations;

import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.model.entities.RentalRecord;
import com.rentalHive.rentalHive.model.entities.User;
import com.rentalHive.rentalHive.repository.IEquipmentRepo;
import com.rentalHive.rentalHive.repository.IRentalRecordRepo;
import com.rentalHive.rentalHive.repository.IUserRepo;
import com.rentalHive.rentalHive.service.IRentalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RentalRecordServiceImpl implements IRentalRecordService {
    private final IRentalRecordRepo rentalRecordRepo ;
    private final IUserRepo userRepo ;
    private final IEquipmentRepo iEquipmentRepo;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @Override
    public ResponseEntity record(RentalRecord rentalRecord) throws ParseException {
        Optional<User> user = userRepo.findById((rentalRecord.getUser().getUserId()));
        Optional<Equipment> equipment = iEquipmentRepo.findById(rentalRecord.getEquipment().getEquipmentId());

        RentalRecord record = new RentalRecord();
        if (!equipment.isPresent()) {
            return ResponseEntity.badRequest().body("equipment is not present");
        } else if (!user.isPresent()) {
            return ResponseEntity.badRequest().body("user is not present");
        }else if(!checkDate(rentalRecord.getReservationDate(),rentalRecord.getReturnDate())){
            return ResponseEntity.badRequest().body("Return date should be after reservation date");
        }
        else if(!checkDateReserve(rentalRecord.getEquipment().getEquipmentId(),rentalRecord.getReservationDate(),rentalRecord.getReturnDate())){
            return ResponseEntity.badRequest().body("this equipment error");
        }

        record.setEquipment(equipment.get());
        record.setUser(user.get());
        record.setReservationDate(rentalRecord.getReservationDate());
        record.setReturnDate(rentalRecord.getReturnDate());
        rentalRecordRepo.save(record);
        return ResponseEntity.ok("record is successfully");
    }


    boolean checkDate(Date dateReservation, Date dateReturn){
            if (dateReservation.after(dateReturn)) {
                return false;
            }
            return true;
    }

    private boolean checkDateReserve(long equipmentId, Date dateReservation, Date dateReturn) {
        List<RentalRecord> existingReservations = rentalRecordRepo.findByEquipmentId(equipmentId);

        for (RentalRecord existingReservation : existingReservations) {
            if (dateReservation.before(existingReservation.getReturnDate()) &&
                    dateReturn.after(existingReservation.getReservationDate())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ResponseEntity deleteRecord(Long recordId) {
        Optional<RentalRecord> rentalRecordOptional = rentalRecordRepo.findById(recordId);

        if (rentalRecordOptional.isPresent()) {
            RentalRecord rentalRecord = rentalRecordOptional.get();
            rentalRecordRepo.delete(rentalRecord);
            return ResponseEntity.ok("Record deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
