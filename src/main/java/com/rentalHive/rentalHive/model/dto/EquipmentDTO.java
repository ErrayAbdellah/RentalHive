package com.rentalHive.rentalHive.model.dto;

import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.model.entities.User;
import com.rentalHive.rentalHive.model.entities.enums.Status;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EquipmentDTO {

    private long equipmentId ;
    @NotBlank
    private String name;
    @Positive
    private double price;
    @Min(1)
    private int quantity;
    @Enumerated
    private Status status;

    public static EquipmentDTO toDTO(Equipment equipment){
        return EquipmentDTO.builder()
                .equipmentId(equipment.getEquipmentId())
                .name(equipment.getName())
                .price(equipment.getPrice())
                .quantity(equipment.getQuantity())
                .status(equipment.getStatus())
                .build();
    }
}
