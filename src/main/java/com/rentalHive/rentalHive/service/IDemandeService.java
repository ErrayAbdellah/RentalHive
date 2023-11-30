package com.rentalHive.rentalHive.service;

import com.rentalHive.rentalHive.model.dto.DemandeDTO;
import com.rentalHive.rentalHive.model.entities.Demande;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IDemandeService {
    ResponseEntity<String> createDemande(DemandeDTO demandeDTO);
    ResponseEntity<List<Demande>> getAllDemandes();
    Optional<Demande> getDemandeById(Long id);

}
