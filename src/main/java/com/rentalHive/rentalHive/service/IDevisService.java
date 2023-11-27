package com.rentalHive.rentalHive.service;

import com.rentalHive.rentalHive.model.dto.DevisDTO;
import com.rentalHive.rentalHive.model.entities.Devis;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDevisService {
    DevisDTO GenerateDevis(DevisDTO devieDTO);
    List<DevisDTO> findAll();
}
