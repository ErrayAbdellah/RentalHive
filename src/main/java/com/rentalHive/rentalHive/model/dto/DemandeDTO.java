package com.rentalHive.rentalHive.model.dto;

import com.rentalHive.rentalHive.model.entities.enums.Priorite;
import com.rentalHive.rentalHive.model.entities.Equipment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DemandeDTO {
    private int userId;
    private Date demandeDate;
    private Date dateRetour;
    private int reference;
    private Priorite priorite;
    private List<Equipment> equipmentList;
    private List<Long> equipmentIds;
}
