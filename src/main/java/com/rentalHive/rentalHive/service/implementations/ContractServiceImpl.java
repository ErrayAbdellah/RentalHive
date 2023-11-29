package com.rentalHive.rentalHive.service.implementations;

import com.rentalHive.rentalHive.model.ConditionDTO;
import com.rentalHive.rentalHive.model.DevisDTO;
import com.rentalHive.rentalHive.model.dto.ContratDTO;
//import com.rentalHive.rentalHive.model.dto.DevisDTO;
import com.rentalHive.rentalHive.model.entities.Condition;
import com.rentalHive.rentalHive.model.entities.Contrat;
import com.rentalHive.rentalHive.model.entities.Devis;
import com.rentalHive.rentalHive.enums.Status;
import com.rentalHive.rentalHive.model.entities.User;
import com.rentalHive.rentalHive.repository.IContractRep;
import com.rentalHive.rentalHive.service.IContractService;
import com.rentalHive.rentalHive.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ContractServiceImpl implements IContractService {

    private final IContractRep iContractRep;
    private final IUserService userService;

    @Autowired
    public ContractServiceImpl(IContractRep iContractRep, IUserService userService) {
        this.iContractRep = iContractRep;
        this.userService = userService;
    }

    @Override
    public List<ContratDTO> getAllContracts() {
        List<Contrat> contrats = iContractRep.findAll();
        List<ContratDTO> contratDTOs = new ArrayList<>();
        for (Contrat contrat : contrats) {
            contratDTOs.add(convertToDTO(contrat));
        }
        return contratDTOs;
    }

    @Override
    public List<ContratDTO> getContractsByStatus(Status status) {
        List<Contrat> contrats = iContractRep.findByStatus(status);
        List<ContratDTO> contratDTOs = new ArrayList<>();

        for (Contrat contrat : contrats) {
            contratDTOs.add(convertToDTO(contrat));
        }
        return contratDTOs;
    }
    @Override
    public Optional<Optional<User>> getUserById(Long userId) {
        return Optional.ofNullable(userService.getUserById(userId));
    }

    @Override
    public List<ContratDTO> getActiveContractsForUser(Long userId) {
        Optional<User> user = userService.getUserById(userId);
        List<Contrat> activeContracts = iContractRep.findAllByUserAndStatus(user, Status.Actif);
        List<ContratDTO> contratDTOs = new ArrayList<>();
        for (Contrat contrat : activeContracts) {
            contratDTOs.add(convertToDTO(contrat));
        }
        return contratDTOs;
    }


    public ContratDTO convertToDTO(Contrat contrat) {
        ContratDTO contratDTO = new ContratDTO();
        contratDTO.setId(contrat.getId());
        contratDTO.setDescription(contrat.getDescription());
        contratDTO.setRef_code(contrat.getRef_code());
        contratDTO.setStatus(contrat.getStatus());

        List<DevisDTO> devisDTOs = convertDevisListToDTO(contrat.getDevis());
        contratDTO.setDevis(devisDTOs);

        List<ConditionDTO> conditionDTOs = new ArrayList<>();
        for (Condition condition : contrat.getConditions()) {
            conditionDTOs.add(convertConditionToDTO(condition));
        }
        contratDTO.setConditions(conditionDTOs);

        return contratDTO;
    }


    private List<DevisDTO> convertDevisListToDTO(Devis devis) {
        List<DevisDTO> devisDTOList = new ArrayList<>();

        DevisDTO devisDTO = convertDevisToDTO(devis);
        devisDTOList.add(devisDTO);

        return devisDTOList;
    }

    private DevisDTO convertDevisToDTO(Devis devis) {
        DevisDTO devisDTO = new DevisDTO();
        devisDTO.setId(devis.getId());
        devisDTO.setDemandeId(devis.getDemande().getId());
        devisDTO.setTotalPrix(devis.getTotalPrix());
        devisDTO.setCommentaire(devis.getCommentaire());
        return devisDTO;
    }

    private ConditionDTO convertConditionToDTO(Condition condition) {
        ConditionDTO conditionDTO = new ConditionDTO();
        conditionDTO.setId(condition.getId());
        conditionDTO.setDescription(condition.getDescription());
        conditionDTO.setState(condition.getState());
        conditionDTO.setBody(condition.getBody());
        return conditionDTO;
    }
}