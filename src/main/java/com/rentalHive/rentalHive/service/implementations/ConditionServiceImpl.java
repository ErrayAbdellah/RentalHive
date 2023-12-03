package com.rentalHive.rentalHive.service.implementations;

import com.rentalHive.rentalHive.model.ConditionDTO;
import com.rentalHive.rentalHive.model.entities.Condition;
import com.rentalHive.rentalHive.model.entities.Contrat;
import com.rentalHive.rentalHive.repository.IConditionRepo;
import com.rentalHive.rentalHive.repository.IContractRep;
import com.rentalHive.rentalHive.service.IConditionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ConditionServiceImpl  implements IConditionService {
    private final IConditionRepo conditionRepository;
    private final IContractRep contratRepository;

    @Autowired
    public ConditionServiceImpl(IConditionRepo conditionRepository, IContractRep contratRepository) {
        this.conditionRepository = conditionRepository;
        this.contratRepository = contratRepository;
    }

    @Override
    @Transactional
    public ConditionDTO createCondition(ConditionDTO conditionDTO) {
        Condition condition = mapToEntity(conditionDTO);
        condition.setContrat(contratRepository.findById(conditionDTO.getContratId())
                .orElseThrow(() -> new RuntimeException("Contrat not found with ID: " + conditionDTO.getContratId())));

        Condition savedCondition = conditionRepository.save(condition);
        return mapToDTO(savedCondition);
    }

    @Override
    public ConditionDTO getConditionById(Long conditionId) {
        Condition condition = (Condition) conditionRepository.findById(conditionId)
                .orElseThrow(() -> new RuntimeException("Condition not found with ID: " + conditionId));
        return mapToDTO(condition);
    }

    @Override
    @Transactional
    public ConditionDTO updateCondition(Long conditionId, ConditionDTO conditionDTO) {
        Condition existingCondition = (Condition) conditionRepository.findById(conditionId)
                .orElseThrow(() -> new RuntimeException("Condition not found with ID: " + conditionId));

        existingCondition.setDescription(conditionDTO.getDescription());
        existingCondition.setState(conditionDTO.getState());
        existingCondition.setBody(conditionDTO.getBody());


        Condition updatedCondition = conditionRepository.save(existingCondition);
        return mapToDTO(updatedCondition);
    }

    @Override
    @Transactional
    public void deleteCondition(Long conditionId) {
        conditionRepository.deleteById(conditionId);
    }

    private ConditionDTO mapToDTO(Condition condition) {
        ConditionDTO conditionDTO = new ConditionDTO();
        conditionDTO.setId(condition.getId());
        conditionDTO.setDescription(condition.getDescription());
        conditionDTO.setState(condition.getState());
        conditionDTO.setBody(condition.getBody());
        conditionDTO.setContratId(condition.getContrat().getId());
        return conditionDTO;
    }

    private Condition mapToEntity(ConditionDTO conditionDTO) {
        Condition condition = new Condition();
        condition.setDescription(conditionDTO.getDescription());
        condition.setState(conditionDTO.getState());
        condition.setBody(conditionDTO.getBody());
        return condition;
    }
    @Override
    @Transactional
    public ConditionDTO assignConditionToContract(Long conditionId, Long contratId) {
        Optional<Condition> optionalCondition = conditionRepository.findById(conditionId);
        Optional<Contrat> optionalContrat = contratRepository.findById(contratId);

        if (optionalCondition.isPresent() && optionalContrat.isPresent()) {
            Condition condition = (Condition) optionalCondition.get();
            Contrat contrat = optionalContrat.get();

            condition.setContrat(contrat);
            conditionRepository.save(condition);

            return mapToDTO(condition);
        } else {
            throw new RuntimeException("Condition or Contract not found with provided IDs.");
        }
    }

    @Override
    public List<ConditionDTO> getAllConditions() {
        List<Condition> conditions = conditionRepository.getAllConditions();
        List<ConditionDTO> conditionDTOs = conditions.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return conditionDTOs;
    }
}
