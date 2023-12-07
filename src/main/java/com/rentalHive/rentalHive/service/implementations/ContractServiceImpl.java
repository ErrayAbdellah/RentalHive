package com.rentalHive.rentalHive.service.implementations;

import com.rentalHive.rentalHive.PdfUtils.PdfGenerator;
import com.rentalHive.rentalHive.controller.ArchieveController;
import com.rentalHive.rentalHive.enums.State;
import com.rentalHive.rentalHive.model.ConditionDTO;
import com.rentalHive.rentalHive.model.DevisDTO;
import com.rentalHive.rentalHive.model.dto.ContratDTO;
import com.rentalHive.rentalHive.model.entities.Condition;
import com.rentalHive.rentalHive.model.entities.Contrat;
import com.rentalHive.rentalHive.enums.Status;
import com.rentalHive.rentalHive.model.entities.Devis;
import com.rentalHive.rentalHive.model.entities.User;
import com.rentalHive.rentalHive.repository.IConditionRepo;
import com.rentalHive.rentalHive.repository.IContractRep;
import com.rentalHive.rentalHive.service.IContractService;
import com.rentalHive.rentalHive.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ContractServiceImpl implements IContractService {

    private final IContractRep iContractRep;
    private final IUserService userService;
    private final ArchieveController archiveController;
    private final IConditionRepo conditionRepo;

    @Autowired
    public ContractServiceImpl(IContractRep iContractRep, IUserService userService, ArchieveController archiveController, IConditionRepo conditionRepo) {
        this.iContractRep = iContractRep;
        this.userService = userService;
        this.conditionRepo = conditionRepo;
        this.archiveController = archiveController;
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
    public ContratDTO markContractAsCompleted(Long contractId) {
        Optional<Contrat> optionalContrat = iContractRep.findById(contractId);
        if (optionalContrat.isPresent()) {
            Contrat contrat = optionalContrat.get();

            contrat.setStatus(Status.Completed);

            archiveController.archiveEntity("Contrat", contrat.getId(), "System", "Contract completed");

            iContractRep.save(contrat);

            return convertToDTO(contrat);
        } else {
            return null;
        }
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

    public ContratDTO archiveContrat(Long contratId, String archivedBy, String archivedReason) {
        Optional<Contrat> optionalContrat = iContractRep.findById(contratId);
        if (optionalContrat.isPresent()) {
            Contrat contrat = optionalContrat.get();

            contrat.setStatus(Status.Archived);
            iContractRep.save(contrat);

            return convertToDTO(contrat);
        } else {
            return null;
        }
    }

    @Override
    public ContratDTO createContract(Devis devis, List<ConditionDTO> conditionDTOList) throws IOException {
        Contrat contract = new Contrat();
        contract.setDevis(devis);
        contract.setDescription("Detailed contract description");
        contract.setRef_code(UUID.randomUUID());
        contract.setStatus(Status.Actif);
        contract.setStartDate(LocalDate.now());
        contract.setEndDate(LocalDate.now().plusDays(364));
        Optional<User> optionalUser = userService.getUserById(devis.getDemande().getUser().getUserId());
        optionalUser.ifPresent(contract::setUser);
        Contrat savedContrat = iContractRep.save(contract);
        for (ConditionDTO conditionDTO : conditionDTOList){
            conditionDTO.setContratId(savedContrat.getId());
            Condition condition = convertConditionToDTO(conditionDTO, savedContrat);
            conditionRepo.save(condition);
        }

        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD_HH-MM-SS");
        String currentDateTime = dateFormat.format(new Date());
        String filePath = "PDFs/" + currentDateTime + ".pdf";
        PdfGenerator.generate(savedContrat, conditionDTOList, filePath);
        return convertToDTO(savedContrat);
    }

    private void addConditionsToContract(Contrat contract) {
        List<ConditionDTO> conditionDTOs = generateConditions(contract.getConditions());

        List<Condition> conditions = convertConditionDTOsToEntities(conditionDTOs);
        contract.setConditions(conditions);
    }

    private List<ConditionDTO> generateConditions(List<Condition> conditionList) {
        List<ConditionDTO> conditionDTOList = new ArrayList<>();

        for (Condition condition : conditionList) {
            ConditionDTO conditionDTO = convertConditionToDTO(condition);
            conditionDTOList.add(conditionDTO);
        }

        return conditionDTOList;
    }


    private ConditionDTO mapToDTO(Condition condition) {
        ConditionDTO conditionDTO = new ConditionDTO(
                condition.getId(),
                condition.getDescription(),
                condition.getState().toString(),
                condition.getBody(),
                condition.getContrat().getId()
        );

        return conditionDTO;
    }

    private List<Condition> convertConditionDTOsToEntities(List<ConditionDTO> conditionDTOs) {
        List<Condition> conditions = new ArrayList<>();
        for (ConditionDTO conditionDTO : conditionDTOs) {
            Condition condition = new Condition();
            condition.setDescription(conditionDTO.getDescription());
            condition.setState(conditionDTO.getState());
            condition.setBody(conditionDTO.getBody());
            conditions.add(condition);
        }
        return conditions;
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
        conditionDTO.setState(State.valueOf(condition.getState().toString()));
        conditionDTO.setBody(condition.getBody());
        conditionDTO.setContratId(condition.getContrat().getId());
        return conditionDTO;
    }

    private Condition convertConditionToDTO(ConditionDTO conditionDTO, Contrat contrat) {
        Condition condition = new Condition(conditionDTO.getId(), conditionDTO.getDescription(), conditionDTO.getState(), conditionDTO.getBody(), contrat);
        return condition;
    }

    @Override
    public Optional<Contrat> getContractById(Long id) {
        return iContractRep.findById(id);
    }
}