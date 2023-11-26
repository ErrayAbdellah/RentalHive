package com.rentalHive.rentalHive.model.entities;

import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.enums.Category;
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

    @Column(name = "quantity")
    private int quantity;

    @OneToMany(mappedBy = "equipment")
    private List<RentalRecord> rentalRecords;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

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
}
