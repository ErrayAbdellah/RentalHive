package com.rentalHive.rentalHive.model.entities;

import java.util.*;

import com.rentalHive.rentalHive.model.dto.RentalRecordDTO;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class RentalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private long reservationId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "reservation_date")
    private Date reservationDate;

    @Column(name = "return_date")
    private Date returnDate;

    public static RentalRecord ToRentalRecord(RentalRecordDTO recordDTO){
        return RentalRecord.builder()
                .reservationId(recordDTO.getReservationId())
                .user(recordDTO.getUser())
                .equipment(recordDTO.getEquipment())
                .reservationDate(recordDTO.getReservationDate())
                .returnDate(recordDTO.getReturnDate())
                .build();
    }
    @Override
    public String toString() {
        return "RentalRecord{" +
                "reservationId=" + reservationId +
                ", equipment=" + (equipment != null ? equipment.getEquipmentId() : null) +
                ", user=" + (user != null ? user.getUserId() : null) +
                ", reservationDate=" + reservationDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
