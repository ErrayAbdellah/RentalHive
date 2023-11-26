package com.rentalHive.rentalHive.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "image_id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

}
