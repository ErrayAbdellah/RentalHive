package com.rentalHive.rentalHive.service;

import com.rentalHive.rentalHive.model.ConditionDTO;
import com.rentalHive.rentalHive.model.dto.ContratDTO;
import com.rentalHive.rentalHive.enums.Status;
import com.rentalHive.rentalHive.model.entities.Contrat;
import com.rentalHive.rentalHive.model.entities.Devis;
import com.rentalHive.rentalHive.model.entities.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public interface IContractService {
    List<ContratDTO> getAllContracts();

    ContratDTO markContractAsCompleted(Long contractId);

    List<ContratDTO> getContractsByStatus(Status status);

    Optional<Optional<User>> getUserById(Long userId);

    List<ContratDTO> getActiveContractsForUser(Long userId);

    ContratDTO createContract(Devis devis, List<ConditionDTO> conditionDTOList) throws IOException;
    Optional<Contrat> getContractById(Long id);

}
