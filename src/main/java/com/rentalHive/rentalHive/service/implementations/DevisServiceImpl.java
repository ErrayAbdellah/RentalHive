package com.rentalHive.rentalHive.service.implementations;

import com.rentalHive.rentalHive.PricingUtils.PricingCalculator;
import com.rentalHive.rentalHive.enums.devisStatus;
import com.rentalHive.rentalHive.model.dto.DemandeDTO;
import com.rentalHive.rentalHive.model.dto.DevisDTO;
import com.rentalHive.rentalHive.model.entities.Demande;
import com.rentalHive.rentalHive.model.entities.Devis;
import com.rentalHive.rentalHive.repository.IDevisRepo;
import com.rentalHive.rentalHive.service.IDevisService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DevisServiceImpl implements IDevisService {

    @Autowired
    private final IDevisRepo devisRepo;

    public DevisServiceImpl(IDevisRepo devisRepo) {
        this.devisRepo = devisRepo;
    }


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
}
