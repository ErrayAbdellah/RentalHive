package com.rentalHive.rentalHive.model.entities;

import com.rentalHive.rentalHive.enums.Type;
import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "VARCHAR(20) DEFAULT 'OfficeEquipment'")
    private Type type;
    @OneToMany(mappedBy = "equipment")
    private List<RentalRecord> rentalRecords;


    public List<RentalRecord> getRentalRecords() {
        return rentalRecords;
    }
    public static Equipment ToEquipment(EquipmentDTO equipmentDTO){
        return Equipment.builder()
                .equipmentId(equipmentDTO.getEquipmentId())
                .name(equipmentDTO.getName())
                .type(equipmentDTO.getType())
                .price(equipmentDTO.getPrice())
                .build();
    }
}
