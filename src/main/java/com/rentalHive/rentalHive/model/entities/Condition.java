package com.rentalHive.rentalHive.model.entities;

<<<<<<< HEAD
=======
import com.rentalHive.rentalHive.enums.State;
>>>>>>> 5899f876471af3e34c193c1d80da61954d703358
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
<<<<<<< HEAD
    private Long id;
=======
    private long id;
>>>>>>> 5899f876471af3e34c193c1d80da61954d703358
    @Column(name = "description")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "State")
<<<<<<< HEAD
    private String state;
=======
    private State state;
>>>>>>> 5899f876471af3e34c193c1d80da61954d703358
    @ManyToOne
    @JoinColumn(name = "contrat_id", nullable = false)
    private Contrat contrat;
}
