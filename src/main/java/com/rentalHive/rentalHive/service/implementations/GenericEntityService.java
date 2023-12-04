package com.rentalHive.rentalHive.service.implementations;

import com.rentalHive.rentalHive.model.entities.Contrat;
import com.rentalHive.rentalHive.model.entities.Demande;
import com.rentalHive.rentalHive.model.entities.Devis;
import com.rentalHive.rentalHive.service.implementations.ContractServiceImpl;
import com.rentalHive.rentalHive.service.implementations.DemandeServiceImpl;
import com.rentalHive.rentalHive.service.implementations.DevisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class GenericEntityService {
    private final DevisServiceImpl devisService;
    private final ContractServiceImpl contractService;
    private final DemandeServiceImpl demandeService;

    @Autowired
    public GenericEntityService(
            DevisServiceImpl devisService,
            ContractServiceImpl contractService,
            DemandeServiceImpl demandeService) {
        this.devisService = devisService;
        this.contractService = contractService;
        this.demandeService = demandeService;
    }


    public Optional<Devis> getDevisById(Long id) {
        return devisService.getDevisById(id);
    }

    public Optional<Contrat> getContractById(Long id) {
        return contractService.getContractById(id);
    }

    public Optional<Demande> getDemandeById(Long id) {
        return demandeService.getDemandeById(id);
    }
}