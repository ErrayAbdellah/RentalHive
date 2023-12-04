package com.rentalHive.rentalHive.service;

import com.rentalHive.rentalHive.enums.State;
import com.rentalHive.rentalHive.model.dto.DemandeDTO;
import com.rentalHive.rentalHive.model.entities.Demande;
import jakarta.annotation.Nullable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IDemandeService {
    ResponseEntity<String> createDemande(DemandeDTO demandeDTO);
    ResponseEntity<List<Demande>> getAllDemandes(@Nullable State state);
}
