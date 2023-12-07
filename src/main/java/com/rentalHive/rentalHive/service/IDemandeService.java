package com.rentalHive.rentalHive.service;

import com.rentalHive.rentalHive.enums.State;
import com.rentalHive.rentalHive.model.dto.DemandeDTO;
import com.rentalHive.rentalHive.model.dto.DemandeDTOforDevis;
import com.rentalHive.rentalHive.model.entities.Demande;
import jakarta.annotation.Nullable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IDemandeService {
    ResponseEntity<String> createDemande(DemandeDTO demandeDTO);
    ResponseEntity<List<DemandeDTOforDevis>> getAllDemandes(@Nullable State state);
    Optional<Demande> getDemandeById(Long id);
}