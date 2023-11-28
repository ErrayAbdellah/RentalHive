package com.rentalHive.rentalHive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevisDTO {
    private Long id;
    private Long demandeId;
    private float totalPrix;
    private String commentaire;
}