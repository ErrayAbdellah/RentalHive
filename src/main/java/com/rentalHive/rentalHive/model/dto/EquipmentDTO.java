package com.rentalHive.rentalHive.model.dto;

import com.rentalHive.rentalHive.model.entities.enums.Status;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDTO {
    @NotNull
    private long equipmentId;
    @NotBlank
    private String name;
    @Positive
    private double price;
    @Min(1)
    private int quantity;
    @Enumerated
    private Status status;
}
