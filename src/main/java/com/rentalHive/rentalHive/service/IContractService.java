package com.rentalHive.rentalHive.service;

import com.rentalHive.rentalHive.model.dto.ContratDTO;
import com.rentalHive.rentalHive.model.entities.Contrat;
import com.rentalHive.rentalHive.enums.Status;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IContractService {
    List<ContratDTO> getAllContracts();
    List<ContratDTO> getContractsByStatus(Status status);
    List<ContratDTO> getActiveContractsForUser(Long userId);
}
