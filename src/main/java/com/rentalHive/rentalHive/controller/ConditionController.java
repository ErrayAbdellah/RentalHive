package com.rentalHive.rentalHive.controller;

import com.rentalHive.rentalHive.model.ConditionDTO;
import com.rentalHive.rentalHive.service.IConditionService;
import com.rentalHive.rentalHive.service.implementations.ConditionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/conditions")
public class ConditionController {

    private final ConditionServiceImpl conditionService;

    @Autowired
    public ConditionController(ConditionServiceImpl conditionService) {
        this.conditionService =  conditionService;
    }

    @PostMapping
    public ResponseEntity<ConditionDTO> createCondition(@RequestBody ConditionDTO conditionDTO) {
        ConditionDTO createdCondition = conditionService.createCondition(conditionDTO);
        return new ResponseEntity<>(createdCondition, HttpStatus.CREATED);
    }

    @GetMapping("/{conditionId}")
    public ResponseEntity<ConditionDTO> getConditionById(@PathVariable Long conditionId) {
        ConditionDTO conditionDTO = conditionService.getConditionById(conditionId);
        return ResponseEntity.ok(conditionDTO);
    }

    @PutMapping("/{conditionId}")
    public ResponseEntity<ConditionDTO> updateCondition(
            @PathVariable Long conditionId,
            @RequestBody ConditionDTO conditionDTO
    ) {
        ConditionDTO updatedCondition = conditionService.updateCondition(conditionId, conditionDTO);
        return ResponseEntity.ok(updatedCondition);
    }

    @DeleteMapping("/{conditionId}")
    public ResponseEntity<Void> deleteCondition(@PathVariable Long conditionId) {
        conditionService.deleteCondition(conditionId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/assign/{conditionId}/to-contract/{contractId}")
    public ResponseEntity<ConditionDTO> assignConditionToContract(
            @PathVariable Long conditionId,
            @PathVariable Long contractId
    ) {
        ConditionDTO assignedCondition = conditionService.assignConditionToContract(conditionId, contractId);
        return new ResponseEntity<>(assignedCondition, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ConditionDTO>> getAllConditions() {
        List<ConditionDTO> conditions = conditionService.getAllConditions();
        return ResponseEntity.ok(conditions);
    }
}
