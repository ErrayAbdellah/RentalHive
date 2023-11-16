package com.rentalHive.rentalHive.model.dto;

import com.rentalHive.rentalHive.model.enums.Status;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDTO {
    private long equipmentId;
    private String name;
    private double price;
    private int quantity;
    private Status status;
}
