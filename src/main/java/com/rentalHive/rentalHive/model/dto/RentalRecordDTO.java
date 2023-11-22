package com.rentalHive.rentalHive.model.dto;

import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.model.entities.RentalRecord;
import com.rentalHive.rentalHive.model.entities.User;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentalRecordDTO {
    private long reservationId;
    @NotNull(message = "Equipment is required")
    private Equipment equipment;
    @NotNull(message = "User is required")
    private User user;
    @NotNull(message = "reservationDate is required")
    private Date reservationDate;
    @NotNull(message = "returnDate is required")
    private Date returnDate;

    public static RentalRecordDTO toDTO(RentalRecord rentalRecord){
        return RentalRecordDTO.builder()
                .reservationId(rentalRecord.getReservationId())
                .equipment(rentalRecord.getEquipment())
                .reservationDate(rentalRecord.getReservationDate())
                .returnDate(rentalRecord.getReturnDate())
                .build();
    }
}
