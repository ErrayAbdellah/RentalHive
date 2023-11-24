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

    private long id;
    @Column(name = "description")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "State")

    private State state;
    @ManyToOne
    @JoinColumn(name = "contrat_id", nullable = false)
    private Contrat contrat;
}
