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

    @OneToOne
    @JoinColumn(name = "demande_id", nullable = false, unique = true)
    private Demande demande;
    private Long id;
    @Column(name = "total")
    private float totalPrix;
    @Column(name = "commentaire")
    private String commentaire;
//=======
//    private long id;
//
//    @Column(name = "total")
//    private float totalPrix;
//
//    @Column(name = "commentaire")
//    private String commentaire;
//
//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "demande_id", nullable = false, unique = true)
//    private Demande demande;
//>>>>>>> 5899f876471af3e34c193c1d80da61954d703358
}
