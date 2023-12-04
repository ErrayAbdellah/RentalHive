package com.rentalHive.rentalHive.controller;

import com.rentalHive.rentalHive.model.dto.ContratDTO;
import com.rentalHive.rentalHive.model.entities.Contrat;
import com.rentalHive.rentalHive.enums.Status;
import com.rentalHive.rentalHive.repository.IContractRep;
import com.rentalHive.rentalHive.service.IContractService;
import com.rentalHive.rentalHive.service.implementations.ContractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/contract")
public class ContratController {

    private final IContractRep iContractRep;
    private final ContractServiceImpl contractService;

    @Autowired
    public ContratController(IContractRep iContractRep, ContractServiceImpl contractService) {
        this.iContractRep = iContractRep;
        this.contractService = contractService;
    }
    @GetMapping("/actifs")
    public ResponseEntity<List<ContratDTO>> getActiveContracts(@RequestParam Long userId) {
        try {
            List<ContratDTO> activeContracts = contractService.getActiveContractsForUser(userId);
            return new ResponseEntity<>(activeContracts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("all")
    public ResponseEntity<List<ContratDTO>> getAllContract()
    {
        try {
            List<ContratDTO> allContract = contractService.getAllContracts();
            return new ResponseEntity<>(allContract, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

