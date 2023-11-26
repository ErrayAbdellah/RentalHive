package com.rentalHive.rentalHive.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "image_id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;
    @Lob
    @Column(name = "image_data", nullable = false)
    private byte[] imageData;
    @Column(name = "image_name", nullable = false)
    private String imageName;
    @Column(name = "image_format", nullable = false)
    private String imageFormat;
    @Column(name = "upload_date", nullable = false)
    private Date uploadDate;

}
