package com.rentalHive.rentalHive.service.implementations;

import com.rentalHive.rentalHive.model.dto.DemandeDTO;
import com.rentalHive.rentalHive.model.entities.Demande;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.model.entities.User;
import com.rentalHive.rentalHive.repository.IDemandeRepo;
import com.rentalHive.rentalHive.repository.IEquipmentRepo;
import com.rentalHive.rentalHive.repository.IUserRepo;
import com.rentalHive.rentalHive.service.IDemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import java.util.List;

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
    public ResponseEntity<String> createDemande(DemandeDTO demandeDTO, List<Long> equipmentIds, int userId) {
        User user = userRepo.findById(userId).orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        Demande demande = new Demande();
//        demande.setDemande_date(demandeDTO.getDemandeDate());
//        demande.setDate_retour(demandeDTO.getDateRetour());
//        demande.setReference(demandeDTO.getReference());
//        demande.setPriorite(demandeDTO.getPriorite());
//        demande.setState(demandeDTO.getState());
        demande.setUser(user);

        List<Equipment> equipmentList = equipmentRepo.findAllById(equipmentIds);
        demande.setEquipment(equipmentList);

        try {
            demandeRepo.save(demande);

            return ResponseEntity.ok("Record has been created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create the record");
        }
    }

    @Override
    public ResponseEntity<List<Demande>> getAllDemandes() {
        List<Demande> demandes = demandeRepo.findAll();

        if (demandes.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(demandes);
        }
    }
}