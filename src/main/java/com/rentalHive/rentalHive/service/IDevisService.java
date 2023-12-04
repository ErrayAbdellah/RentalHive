package com.rentalHive.rentalHive.service;

import com.rentalHive.rentalHive.model.dto.ContratDTO;
import com.rentalHive.rentalHive.model.entities.Devis;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IDevisService {
    String approveDevis(Long devisId);
    Optional<Devis> getDevisById(Long id);
}
