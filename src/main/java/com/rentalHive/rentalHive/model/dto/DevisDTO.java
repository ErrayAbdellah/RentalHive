package com.rentalHive.rentalHive.model.dto;

import com.rentalHive.rentalHive.enums.devisStatus;
import com.rentalHive.rentalHive.model.entities.Demande;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DevisDTO {

    private DemandeDTO demande;

    private double totalPrix;

    private String commentaire;
    private devisStatus devisStatus;

}
