package com.rentalHive.rentalHive.service.implementations;

import com.rentalHive.rentalHive.model.dto.RentalRecordDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.model.entities.RentalRecord;
import com.rentalHive.rentalHive.model.entities.User;
import com.rentalHive.rentalHive.repository.IEquipmentRepo;
import com.rentalHive.rentalHive.repository.IRentalRecordRepo;
import com.rentalHive.rentalHive.repository.IUserRepo;
import com.rentalHive.rentalHive.service.IRentalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Primary
public class RentalRecordServiceImpl implements IRentalRecordService {
    private final IRentalRecordRepo rentalRecordRepo ;
    private final IUserRepo userRepo ;
    private final IEquipmentRepo equipmentRepo;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @Override
    public ResponseEntity record(RentalRecordDTO recordDTO){
        RentalRecord  rentalRecord = RentalRecord.ToRentalRecord(recordDTO);
        Optional<User> user = userRepo.findById((rentalRecord.getUser().getUserId()));
        Optional<Equipment> equipment = equipmentRepo.findById(rentalRecord.getEquipment().getEquipmentId());

        RentalRecord record = new RentalRecord();
        if (!equipment.isPresent()) {
            return ResponseEntity.badRequest().body("equipment is not present");
        } else if (!user.isPresent()) {
            return ResponseEntity.badRequest().body("user is not present");
        }else if(!checkDate(rentalRecord.getReservationDate(),rentalRecord.getReturnDate())){
            return ResponseEntity.badRequest().body("Return date should be after reservation date");
        }else if(!checkDateReserve(rentalRecord.getEquipment().getEquipmentId(),rentalRecord.getReservationDate(),rentalRecord.getReturnDate())){
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

    @Override
    public ResponseEntity<List<RentalRecordDTO>> getEquipmentRentalHistory(long equipmentId) {
        Optional<Equipment> optionalEquipment = equipmentRepo.findById(equipmentId);

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

}