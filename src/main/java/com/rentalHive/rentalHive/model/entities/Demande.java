package com.rentalHive.rentalHive.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rentalHive.rentalHive.model.dto.DemandeDTO;
import com.rentalHive.rentalHive.model.entities.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "demande_id")
    private Long id;
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
    @JoinTable(
            name = "demande_equipement",
            joinColumns = @JoinColumn(name = "demande_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    @JsonManagedReference
    private List<Equipment> equipment;

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public DemandeDTO toDTO() {
        return DemandeDTO.builder()
                .user(user)
                .demandeDate(demande_date)
                .dateRetour(date_retour)
                .reference(reference)
                .priorite(priorite)
                .state(state)
                .equipmentIds(getEquipmentIds())
                .build();
    }

    private List<Long> getEquipmentIds() {
        return equipment.stream().map(Equipment::getEquipmentId).collect(Collectors.toList());
    }
}
