package com.rentalHive.rentalHive.model.entities;

import com.rentalHive.rentalHive.enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Condition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;

    @ManyToOne
    @JoinColumn(name = "contrat_id")
    private Contrat contrat;
}
