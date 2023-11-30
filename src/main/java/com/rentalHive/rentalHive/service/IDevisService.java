package com.rentalHive.rentalHive.service;

import com.rentalHive.rentalHive.model.dto.ContratDTO;
import org.springframework.stereotype.Service;

@Service
public interface IDevisService {
    ContratDTO approveDevis(Long devisId);
}
