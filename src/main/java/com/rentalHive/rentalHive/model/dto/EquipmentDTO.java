package com.rentalHive.rentalHive.model.dto;

import com.rentalHive.rentalHive.model.entities.enums.Status;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EquipmentDTO {
    private long equipmentId;
    private String name;
    private double price;
    private int quantity;
    private Status status;

}
