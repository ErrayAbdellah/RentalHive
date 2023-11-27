package com.rentalHive.rentalHive.controller;

import com.rentalHive.rentalHive.model.entities.Contrat;
import com.rentalHive.rentalHive.enums.Status;
import com.rentalHive.rentalHive.repository.IContractRep;
import com.rentalHive.rentalHive.service.IContractService;
import com.rentalHive.rentalHive.service.implementations.ContractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/status/{status}")
    public List<Contrat> getContractsByStatus(@PathVariable Status status) {
        List<Contrat> contracts = contractService.getContractsByStatus(status);
        return ResponseEntity.ok(contracts).getBody();
    }
    }

