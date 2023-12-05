package com.rentalHive.rentalHive.model.entities;

import com.rentalHive.rentalHive.enums.devisStatus;
import com.rentalHive.rentalHive.service.ArchivableEntity;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Devis implements ArchivableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "devis_id", nullable = false)
    private Long id;
    @OneToOne
    @JoinColumn(name = "demande_id", nullable = false, unique = true)
    private Demande demande;

    @Column(name = "approved")
    private boolean approved;

    @Column(name = "total")
    private double totalPrix;

    @Column(name = "commentaire")
    private String commentaire;

    @Override
    public String getEntityType() {
        return "Devis";
    }
    @Override
    public Long getId()
    {
        return id;
    }
}
