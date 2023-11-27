package com.rentalHive.rentalHive.model.dto;

import com.rentalHive.rentalHive.model.entities.Demande;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DevisDTO {

    private Demande demande;

    private float totalPrix;

    private String commentaire;

}
