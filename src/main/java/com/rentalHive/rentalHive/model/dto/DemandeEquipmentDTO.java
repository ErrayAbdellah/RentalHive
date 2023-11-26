package com.rentalHive.rentalHive.model.dto;

import com.rentalHive.rentalHive.model.entities.DemandeEquipment;
import lombok.Data;

@Data
public class DemandeEquipmentDTO {

    private Long demandeEquipmentId;
    private Long demandeId;
    private Long equipmentId;

    public static DemandeEquipmentDTO fromEntity(DemandeEquipment demandeEquipment) {
        DemandeEquipmentDTO dto = new DemandeEquipmentDTO();
        dto.setDemandeEquipmentId(demandeEquipment.getDemandeEquipementId());
        dto.setDemandeId(demandeEquipment.getDemande().getId());
        dto.setEquipmentId(demandeEquipment.getEquipment().getEquipmentId());
        return dto;
    }

}

