package com.rentalHive.rentalHive.service.implementations;

import com.rentalHive.rentalHive.enums.State;
import com.rentalHive.rentalHive.model.dto.DemandeDTO;
import com.rentalHive.rentalHive.model.dto.DemandeDTOforDevis;
import com.rentalHive.rentalHive.model.entities.Demande;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.model.entities.User;
import com.rentalHive.rentalHive.repository.IDemandeRepo;
import com.rentalHive.rentalHive.repository.IEquipmentRepo;
import com.rentalHive.rentalHive.repository.IUserRepo;
import com.rentalHive.rentalHive.service.IDemandeService;
import jakarta.annotation.Nullable;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DemandeServiceImpl implements IDemandeService {

    private final IDemandeRepo demandeRepo;
    private final IUserRepo userRepo;
    private final IEquipmentRepo equipmentRepo;

    @Autowired
    public DemandeServiceImpl(IDemandeRepo demandeRepo, IUserRepo userRepo,
                              IEquipmentRepo equipmentRepo) {
        this.demandeRepo = demandeRepo;
        this.userRepo = userRepo;
        this.equipmentRepo = equipmentRepo;
    }

    @Override
    public ResponseEntity<String> createDemande(DemandeDTO demandeDTO) {
        int userId = demandeDTO.getUserId();
        List<Long> equipmentIds = demandeDTO.getEquipmentIds();

        User user = userRepo.findById(userId).orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.addMappings(new PropertyMap<DemandeDTO, Demande>() {
            protected void configure() {
                skip(destination.getId());
                map().setDemande_date(source.getDemandeDate());
                map().setDate_retour(source.getDateRetour());
            }
        });

        Demande demande = modelMapper.map(demandeDTO, Demande.class);
        demande.setUser(user);

        List<Equipment> equipmentList = equipmentRepo.findAllById(equipmentIds);
        demande.setEquipment(equipmentList);

        if (!checkDateReserve(demande)) {
            return ResponseEntity.badRequest().body("La nouvelle réservation chevauche une réservation existante.");
        }

        if (!checkDate(demande.getDemande_date(), demande.getDate_retour())) {
            return ResponseEntity.badRequest().body("demandeDate doit être après dateRetour.");
        }

        try {
            demandeRepo.save(demande);
            return ResponseEntity.ok("Le dossier a été créé avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la création du dossier.");
        }
    }

    private boolean checkDate(Date dateReservation, Date dateReturn) {
        return !dateReservation.after(dateReturn);
    }

    private boolean checkDateReserve(Demande demande) {
        List<Demande> existingReservations = demandeRepo.findExistingReservations(
                demande.getEquipment().stream().map(Equipment::getEquipmentId).collect(Collectors.toList()),
                demande.getDemande_date(),
                demande.getDate_retour()
        );

        return existingReservations.isEmpty();
    }



    @Override
    public ResponseEntity<List<DemandeDTOforDevis>> getAllDemandes(@Nullable State state) {
        List<DemandeDTOforDevis> demandes = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        if (state != null){
            for (Demande demande : demandeRepo.findDemandeByState(state)){
                demandes.add(modelMapper.map(demande, DemandeDTOforDevis.class));
            }
        } else {
            for (Demande demande : demandeRepo.findAll()){
                demandes.add(modelMapper.map(demande, DemandeDTOforDevis.class));
            }
        }

        if (demandes.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(demandes);
        }
    }

    @Override
    public Optional<Demande> getDemandeById(Long id) {
        return demandeRepo.findById(id);
    }
}