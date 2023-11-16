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

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "reservation_date")
    private Date reservationDate;

    @Column(name = "return_date")
    private Date returnDate;

}
