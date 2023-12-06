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

    @Column(name = "image")
    private String image;



    @JsonIgnore
    @OneToMany(mappedBy = "equipment")
    private List<RentalRecord> rentalRecords;

    @Enumerated(EnumType.STRING)

    @Column(name = "category")
    private Category category;

    @JsonBackReference
    @ManyToMany(mappedBy = "equipment")
    private List<Demande> demandes;

//    @OneToMany(mappedBy = "equipment")
//    private List<Image> images ;


    @JsonBackReference
    public List<Demande> getDemandes() {
        return demandes;
    }

    @JsonIgnore
    public List<RentalRecord> getRentalRecords() {
        return rentalRecords;
    }

    public static Equipment ToEquipment(EquipmentDTO equipmentDTO){
        return Equipment.builder()
                .equipmentId(equipmentDTO.getEquipmentId())
                .name(equipmentDTO.getName())
                .category(equipmentDTO.getCategory())
                .price(equipmentDTO.getPrice())
                .build();
    }

    public EquipmentDTO toDTO() {
        return EquipmentDTO.builder()
                .equipmentId(equipmentId)
                .name(name)
//                .price(price)
//                .category(category)
                .build();
    }
}
