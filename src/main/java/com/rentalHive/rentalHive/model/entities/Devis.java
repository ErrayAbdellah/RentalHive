package com.rentalHive.rentalHive.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Devis {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "total")
    private float totalPrix;

    @Column(name = "commentaire")
    private String commentaire;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "demande_id", nullable = false, unique = true)
    private Demande demande;
}
