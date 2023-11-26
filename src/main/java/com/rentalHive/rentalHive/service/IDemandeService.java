package com.rentalHive.rentalHive.service;

import com.rentalHive.rentalHive.model.dto.DemandeDTO;
import com.rentalHive.rentalHive.model.entities.Demande;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IDemandeService {
    ResponseEntity<String> createDemande(DemandeDTO demandeDTO, List<Long> equipmentIds, int userId);
}