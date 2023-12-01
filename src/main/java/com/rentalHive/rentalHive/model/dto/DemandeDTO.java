package com.rentalHive.rentalHive.model.dto;

import com.rentalHive.rentalHive.model.entities.User;
import com.rentalHive.rentalHive.enums.Priorite;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.enums.State;
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
    private User user;
    private long id;
    private Date demande_date;
    private Date date_retour;
    private int reference;
    private Priorite priorite;
    private State state;
    private List<Long> equipmentIds;
}
