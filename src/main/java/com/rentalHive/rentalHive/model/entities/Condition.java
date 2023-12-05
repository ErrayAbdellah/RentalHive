package com.rentalHive.rentalHive.model.entities;

import com.rentalHive.rentalHive.enums.State;
import com.rentalHive.rentalHive.enums.Status;
import com.rentalHive.rentalHive.service.ArchivableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "conditions")
public class Condition implements ArchivableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "condition_id", nullable = false)
    private Long id;
    @Column(name = "description")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;
    @Column(name = "body")
    private String body;
    @ManyToOne
    @JoinColumn(name = "contrat_id", nullable = false,      foreignKey = @ForeignKey(name = "FK_CONDITIONS_ON_CONTRAT_NEW"))
    private Contrat contrat;
    @Override
    public  Long getId()
    {
        return  id;
    }

    @Override
    public String getEntityType() {
        return "condition";
    }
}
