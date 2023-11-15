package com.rentalHive.rentalHive.dto;

import com.rentalHive.rentalHive.model.entities.Status;
import lombok.Data;

@Data
public class EquipmentDTO {
    private Long equipmentId;
    private String name;
    private double price;
    private int quantity;
    private Status status;


    public EquipmentDTO() {
    }

    public EquipmentDTO(Long equipmentId, String name, double price, int quantity, Status status) {
        this.equipmentId = equipmentId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }
}
