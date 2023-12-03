package com.rentalHive.rentalHive.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", nullable = false)
    private Long id;
//    @Lob
//    @Column(name = "image_data", nullable = false)
//    private byte[] imageData;
//    @Column(name = "image_name", nullable = false)
//    private String imageName;
//    @Column(name = "image_format", nullable = false)
//    private String imageFormat;
//    @Column(name = "upload_date", nullable = false)
//    private Date uploadDate;
//    @Lob
//    private Blob image;
    @Lob
    @Basic(fetch = FetchType.LAZY)  // Fetch image lazily
    @Column(name = "image", nullable = false, columnDefinition = "LONGBLOB")
    private byte[] image;

    private Date date = new Date();
    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;
    public void setImage(byte[] image) {
        this.image = image;
    }

}
