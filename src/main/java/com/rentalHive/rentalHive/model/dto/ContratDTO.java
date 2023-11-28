package com.rentalHive.rentalHive.model.dto;

import com.rentalHive.rentalHive.model.DevisDTO;
import com.rentalHive.rentalHive.enums.Status;
import com.rentalHive.rentalHive.model.ConditionDTO;

import java.util.List;
import java.util.UUID;

public class ContratDTO {
    private Long id;
    private String description;
    private UUID ref_code;
    private Status status;
    private List<ConditionDTO> conditions;
    private List<DevisDTO> devis;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<ConditionDTO> getConditions() {
        return conditions;
    }

    public void setConditions(List<ConditionDTO> conditions) {
        this.conditions = conditions;
    }



    public void setDevis(List<DevisDTO> devis) {
        this.devis = devis;
    }
    public List<DevisDTO> getDevis() {
        return devis;
    }
}
