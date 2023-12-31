package com.rentalHive.rentalHive.model.dto;

import com.rentalHive.rentalHive.model.entities.Equipment;

import com.rentalHive.rentalHive.enums.Category;
import jakarta.persistence.Enumerated;
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
    @Enumerated
    private Category category;

}
