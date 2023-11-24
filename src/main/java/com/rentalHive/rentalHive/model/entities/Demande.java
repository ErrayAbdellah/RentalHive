package com.rentalHive.rentalHive.model.entities;

import com.rentalHive.rentalHive.enums.Priorite;
import com.rentalHive.rentalHive.enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_demande")
    private long id;
    @ManyToOne
    @JoinColumn(name ="user_id",nullable = false)
    private User user;
    @Column(name = "demande_date")
    private Date demande_date;
    @Column(name = "date_retour")
    private Date date_retour;
    @Column(name = "reference")
    private int reference;
    @Enumerated(EnumType.STRING)
    @Column(name = "priorite")
    private Priorite priorite;
    @Enumerated(EnumType.STRING)
    @Column(name ="state")
    private State state;
    @ManyToMany
    @JoinTable
            (
                    name = "demande_equipement",
                    joinColumns = @JoinColumn(name = "demande_id"),
                    inverseJoinColumns = @JoinColumn(name = "equipemnt_id")
            )
    private List<Equipment> equipment;
}
