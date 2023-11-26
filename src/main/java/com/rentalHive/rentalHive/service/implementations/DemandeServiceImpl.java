package com.rentalHive.rentalHive.service.implementations;// ... (other imports)
import com.rentalHive.rentalHive.model.dto.DemandeDTO;
import com.rentalHive.rentalHive.model.entities.Demande;
import com.rentalHive.rentalHive.model.entities.DemandeEquipment;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.model.entities.User;
import com.rentalHive.rentalHive.repository.IDemandeEquipmentRepo;
import com.rentalHive.rentalHive.repository.IDemandeRepo;
import com.rentalHive.rentalHive.repository.IEquipmentRepo;
import com.rentalHive.rentalHive.repository.IUserRepo;
import com.rentalHive.rentalHive.service.IDemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Service
public class DemandeServiceImpl implements IDemandeService {

    private final IDemandeRepo demandeRepo;
    private final IUserRepo userRepo;
    private final IEquipmentRepo equipmentRepo;
    private final IDemandeEquipmentRepo demandeEquipementRepo;

    @Autowired
    public DemandeServiceImpl(IDemandeRepo demandeRepo, IUserRepo userRepo,
                              IEquipmentRepo equipmentRepo, IDemandeEquipmentRepo demandeEquipementRepo) {
        this.demandeRepo = demandeRepo;
        this.userRepo = userRepo;
        this.equipmentRepo = equipmentRepo;
        this.demandeEquipementRepo = demandeEquipementRepo;
    }

    @Override
    public ResponseEntity<String> createDemande(DemandeDTO demandeDTO, List<Long> equipmentIds, int userId) {
        User user = userRepo.findById(userId).orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        Demande demande = new Demande();
        demande.setDemande_date(demandeDTO.getDemandeDate());
        demande.setDate_retour(demandeDTO.getDateRetour());
        demande.setReference(demandeDTO.getReference());
        demande.setPriorite(demandeDTO.getPriorite());
        demande.setUser(user);

        List<Equipment> equipmentList = equipmentRepo.findAllById(equipmentIds);
        demande.setEquipment(equipmentList);

        try {
            demande = demandeRepo.save(demande);

            for (Equipment equipment : equipmentList) {
                DemandeEquipment demandeEquipment = DemandeEquipment.builder()
                        .demande(demande)
                        .equipment(equipment)
                        .build();

                demandeEquipementRepo.save(demandeEquipment);
            }

            return ResponseEntity.ok("Record has been created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create the record");
        }
    }
}