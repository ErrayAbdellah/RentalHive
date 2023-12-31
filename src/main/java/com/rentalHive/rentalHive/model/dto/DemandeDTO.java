package com.rentalHive.rentalHive.model.dto;

import com.rentalHive.rentalHive.enums.Priorite;
import com.rentalHive.rentalHive.enums.State;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DemandeDTO {
    @NotNull(message = "User ID cannot be null")
    private Integer userId;

    private Long demandeID;

    @NotNull(message = "Demande date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date demandeDate;

    @NotNull(message = "Date retour cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateRetour;

    @Min(value = 1, message = "Reference must be greater than or equal to 1")
    private Integer reference;

    @NotNull(message = "Priorite cannot be null")
    @Enumerated(EnumType.STRING)
    private Priorite priorite;

    @NotNull(message = "State cannot be null")
    @Enumerated(EnumType.STRING)
    private State state;

    @NotEmpty(message = "Equipment IDs list cannot be empty")
    private List<Long> equipmentIds;
}
