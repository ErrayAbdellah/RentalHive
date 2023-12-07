package com.rentalHive.rentalHive.model.dto;

import com.rentalHive.rentalHive.model.ConditionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractDTOforPDF {
    private List<ConditionDTO> conditionDTOS;
}
