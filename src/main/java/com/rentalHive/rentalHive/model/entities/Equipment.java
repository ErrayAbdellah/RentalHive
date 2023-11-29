package com.rentalHive.rentalHive.model.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.enums.Category;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id")
    private long equipmentId;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;



    @JsonIgnore
    @OneToMany(mappedBy = "equipment")
    private List<RentalRecord> rentalRecords;

    @Enumerated(EnumType.STRING)

    @Column(name = "category")
    private Category category;

    @JsonBackReference
    @ManyToMany(mappedBy = "equipment")
    private List<Demande> demandes;


}
