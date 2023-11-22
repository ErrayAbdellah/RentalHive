package com.rentalHive.rentalHive.model.entities;

import java.util.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "reservation_date")
    private Date reservationDate;

    @Column(name = "return_date")
    private Date returnDate;
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
