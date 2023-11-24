package com.rentalHive.rentalHive.model.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(columnDefinition = "TEXT" , name = "description")
    private  String description;
    @Column(name = "ref_code")
    private UUID ref_code;
    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL)
    private List<Condition> conditions;
}