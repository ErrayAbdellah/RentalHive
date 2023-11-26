package com.rentalHive.rentalHive.model.entities;


import com.rentalHive.rentalHive.model.dto.DemandeEquipmentDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "demande_equipement")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DemandeEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "demande_equipement_id")
    private Long demandeEquipementId;

    @ManyToOne
    @JoinColumn(name = "demande_id", nullable = false)
    private Demande demande;

    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    public static DemandeEquipment toDemandeEquipment(DemandeEquipmentDTO demandeEquipmentDTO) {
        DemandeEquipment demandeEquipment = new DemandeEquipment();
        demandeEquipment.setDemandeEquipementId(demandeEquipmentDTO.getDemandeEquipmentId());

        Demande demande = new Demande();
        demande.setId(demandeEquipmentDTO.getDemandeId());
        demandeEquipment.setDemande(demande);

        Equipment equipment = new Equipment();
        equipment.setEquipmentId(demandeEquipmentDTO.getEquipmentId());
        demandeEquipment.setEquipment(equipment);

        return demandeEquipment;
    }

}

