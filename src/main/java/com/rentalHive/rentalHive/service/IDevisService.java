package com.rentalHive.rentalHive.service;

import com.rentalHive.rentalHive.enums.devisStatus;
import com.rentalHive.rentalHive.model.dto.DevisDTO;
import com.rentalHive.rentalHive.model.entities.Demande;
import com.rentalHive.rentalHive.model.entities.Devis;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IDevisService {
    DevisDTO generateDevis(Demande demande);
    List<DevisDTO> findAll();
    DevisDTO findDevisByDemandeID(long demande_id);
    DevisDTO PatchDevisState(long demande_id , devisStatus status);

    String approveDevis(Long devisId);
    Optional<Devis> getDevisById(Long id);
    boolean isExists(long demande_id);
}
