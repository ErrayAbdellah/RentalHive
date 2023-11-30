package com.rentalHive.rentalHive.service;

import com.rentalHive.rentalHive.model.dto.DevisDTO;
import com.rentalHive.rentalHive.model.entities.Demande;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDevisService {
    DevisDTO generateDevis(Demande demande);
    List<DevisDTO> findAll();
}
