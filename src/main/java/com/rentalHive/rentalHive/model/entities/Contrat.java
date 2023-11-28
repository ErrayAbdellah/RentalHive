package com.rentalHive.rentalHive.model.entities;


import com.rentalHive.rentalHive.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "contrat")
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "contract_id", nullable = false )
    private Long id;
    @Column(columnDefinition = "TEXT" , name = "description")
    private  String description;
    @Column(name = "ref_code")
    private UUID ref_code;
    @Column(name = "user_id")
    private Long userId;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "devis_id", nullable = false, unique = true)
    private Devis devis;



    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL)
    private List<Condition> conditions;

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }
    public void addCondition(Condition condition) {
        conditions.add(condition);
        condition.setContrat(this);
    }
    public void removeCondition(Condition condition) {
        conditions.remove(condition);
        condition.setContrat(null);
    }
}
