package com.rentalHive.rentalHive.model.entities;


import com.rentalHive.rentalHive.enums.Status;
import com.rentalHive.rentalHive.service.ArchivableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "contrat")
public class Contrat implements ArchivableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id", nullable = false)
    private Long id;
    @Column(columnDefinition = "TEXT" , name = "description")
    private  String description;
    @Column(name = "ref_code")
    private UUID ref_code;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name="StartDate")
    private LocalDate StartDate;
    @Column(name = "EndDate")
    private LocalDate EndDate;

    public LocalDate getStartDate() {
        return StartDate;
    }

    public void setStartDate(LocalDate startDate) {
        StartDate = startDate;
    }

    public LocalDate getEndDate() {
        return EndDate;
    }

    public void setEndDate(LocalDate endDate) {
        EndDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getRef_code() {
        return ref_code;
    }

    public void setRef_code(UUID ref_code) {
        this.ref_code = ref_code;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getEntityType() {
        return "Contract";
    }
}
