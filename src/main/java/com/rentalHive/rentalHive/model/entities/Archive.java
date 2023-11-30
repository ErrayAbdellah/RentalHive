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
public class Archive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_archieve", nullable = false)
    private Long id;


    @Column(name = "related_entity_type", nullable = false)
    private String relatedEntityType;

    @Column(name = "related_entity_id", nullable = false)
    private Long relatedEntityId;
    @Column(name = "comment", nullable = true)
    private String comment;
    @Column(name = "archived_date", nullable = false)
    private Date archivedDate;

    @Column(name = "archived_by", nullable = false)
    private String archivedBy;

    @Column(name = "archived_reason", nullable = true)
    private String archivedReason;
}
