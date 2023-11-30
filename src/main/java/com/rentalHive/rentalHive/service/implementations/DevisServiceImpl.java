package com.rentalHive.rentalHive.service.implementations;

import com.rentalHive.rentalHive.model.dto.ContratDTO;
import com.rentalHive.rentalHive.model.entities.Devis;
import com.rentalHive.rentalHive.repository.IDevisRepo;
import com.rentalHive.rentalHive.service.IContractService;
import com.rentalHive.rentalHive.service.IDevisService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DevisServiceImpl implements IDevisService {

    private final IDevisRepo devisRepo;
    private final IContractService contractService;
    public DevisServiceImpl(IDevisRepo devisRepo, IContractService contractService) {
        this.devisRepo = devisRepo;
        this.contractService = contractService;
    }
    @Override
    public ContratDTO approveDevis(Long devisId) {
        Optional<Devis> optionalDevis = devisRepo.findById(devisId);
        if (optionalDevis.isPresent()) {
            Devis devis = optionalDevis.get();
            devis.setApproved(true);
            devisRepo.save(devis);
            contractService.createContract(devis);
            return contractService.createContract(devis);
        }
        throw new RuntimeException("Devis not found with ID: " + devisId);
    }

    @Override
    public Optional<Devis> getDevisById(Long id) {
        return devisRepo.findById(id);
    }

}
