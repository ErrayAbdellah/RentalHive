package com.rentalHive.rentalHive.service.implementations;

import com.rentalHive.rentalHive.model.dto.DevisDTO;
import com.rentalHive.rentalHive.model.entities.Devis;
import com.rentalHive.rentalHive.repository.IDevisRepo;
import com.rentalHive.rentalHive.service.IDevisService;
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
    public DevisDTO GenerateDevis(DevisDTO devieDTO) {
        return null;
    }

    @Override
    public List<DevisDTO> findAll() {
        List<Devis> allDevis = devisRepo.findAll();
        return allDevis.stream().map(devis -> DevisDTO.builder().
                commentaire(devis.getCommentaire()).
                demande(devis.getDemande()).
                build()).collect(Collectors.toList());
    }
}
