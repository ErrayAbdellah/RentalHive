package com.rentalHive.rentalHive.model.entities;

import com.rentalHive.rentalHive.enums.devisStatus;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Devis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToOne
    @JoinColumn(name = "demande_id", nullable = false, unique = true)
    private Demande demande;

    @Column(name = "total")
    private double totalPrix;

    @Column(name = "commentaire")
    private String commentaire;
    @Column(name = "status")
    private devisStatus devisStatus;
}
