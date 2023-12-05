package com.rentalHive.rentalHive.service.implementations;

import com.rentalHive.rentalHive.PricingUtils.PricingCalculator;
import com.rentalHive.rentalHive.enums.devisStatus;
import com.rentalHive.rentalHive.model.dto.DevisDTO;
import com.rentalHive.rentalHive.model.entities.Demande;
import com.rentalHive.rentalHive.model.entities.Devis;
import com.rentalHive.rentalHive.repository.IDevisRepo;
import com.rentalHive.rentalHive.service.IDevisService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import com.rentalHive.rentalHive.model.dto.ContratDTO;
import com.rentalHive.rentalHive.model.entities.Devis;
import com.rentalHive.rentalHive.repository.IDevisRepo;
import com.rentalHive.rentalHive.service.IContractService;
import com.rentalHive.rentalHive.service.IDevisService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DevisServiceImpl implements IDevisService {


    private final IDevisRepo devisRepo;
    private final IContractService contractService;



    @Override
    public DevisDTO generateDevis(Demande demande) {
        double price = PricingCalculator.calculateTotalPrice(demande);
        ModelMapper modelMapper = new ModelMapper();
        Devis devis = Devis.builder().
                devisStatus(devisStatus.PENDING).
                totalPrix(price).
                demande(demande).
                commentaire("Lorem").
                build();
        Devis savedDevis = devisRepo.save(devis);
        DevisDTO devisDTO = modelMapper.map(savedDevis, DevisDTO.class);
        return devisDTO;
    }

    @Override
    public List<DevisDTO> findAll() {
        List<Devis> allDevis = devisRepo.findAll();
        ModelMapper modelMapper = new ModelMapper();
        return allDevis.stream().map(devis -> modelMapper.map(devis, DevisDTO.class)).collect(Collectors.toList());
    }

    @Override
    public DevisDTO findDevisByDemandeID(long demande_id) {
        Optional<Devis> devisByDemandeId = devisRepo.findByDemande_Id(demande_id);
        if(devisByDemandeId.isPresent()){
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(devisByDemandeId.get(), DevisDTO.class);
        }else{
            throw new NoSuchElementException();
        }


    @Override
    public String approveDevis(Long devisId) {
        Optional<Devis> optionalDevis = devisRepo.findById(devisId);
        if (optionalDevis.isPresent()) {
            Devis devis = optionalDevis.get();
            devis.setApproved(true);
            devisRepo.save(devis);
            ContratDTO contratDTO = contractService.createContract(devis);

            if (contratDTO != null) {
                return "Devis approved and contract created successfully!";
            } else {
                return "Error creating contract.";
            }
        } else {
            return "Devis not found with ID: " + devisId;
        }
    }

    @Override
    public Optional<Devis> getDevisById(Long id) {
        return devisRepo.findById(id);
    }

    public DevisDTO PatchDevisState(long demande_id , devisStatus status) {
        Optional<Devis> devis = devisRepo.findByDemande_Id(demande_id);
        if(devis.isPresent()){
            ModelMapper modelMapper = new ModelMapper();
            devis.get().setDevisStatus(status);
            Devis updatedDevis = devisRepo.save(devis.get());
            return modelMapper.map(updatedDevis, DevisDTO.class);
        }else {
            throw new NoSuchElementException();
        }

    }

    @Override
    public boolean isExists(long demande_id) {
        return devisRepo.existsDevisByDemande_Id(demande_id);
    }
}
