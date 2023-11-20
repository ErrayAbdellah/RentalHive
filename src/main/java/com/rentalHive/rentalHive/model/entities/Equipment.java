package com.rentalHive.rentalHive.model.entities;

import com.rentalHive.rentalHive.model.entities.enums.Status;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToMany(mappedBy = "equipment")
    private List<RentalRecord> rentalRecords;

    public List<RentalRecord> getRentalRecords() {
        return rentalRecords;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "equipmentId=" + equipmentId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", status=" + status +
                // Exclude Optional field from toString
//                ", rentalRecords=" + rentalRecords.map(r -> "RentalRecords{...}").orElse(null) +
                '}';
    }
}
