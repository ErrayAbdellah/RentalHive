package com.rentalHive.rentalHive.model.dto;

import lombok.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalRecordDTO {
    private long reservationId;
    private long equipmentId;
    private long userId;
    private Date reservationDate;
    private Date returnDate;
}
