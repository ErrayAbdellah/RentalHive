package com.rentalHive.rentalHive.service;

import com.rentalHive.rentalHive.model.ConditionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IConditionService {
    ConditionDTO createCondition(ConditionDTO conditionDTO);

    ConditionDTO getConditionById(Long conditionId);

    ConditionDTO updateCondition(Long conditionId, ConditionDTO conditionDTO);

    void deleteCondition(Long conditionId);
    ConditionDTO assignConditionToContract(Long conditionId, Long contratId);

    List<ConditionDTO> getAllConditions();
}
