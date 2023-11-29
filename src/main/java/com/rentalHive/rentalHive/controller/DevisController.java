package com.rentalHive.rentalHive.controller;

import com.rentalHive.rentalHive.model.dto.ContratDTO;
import com.rentalHive.rentalHive.model.entities.Devis;
import com.rentalHive.rentalHive.repository.IDevisRepo;
import com.rentalHive.rentalHive.service.IDevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/devis")
public class DevisController {
    private final IDevisService devisService;


    @Autowired
    public DevisController(IDevisService devisService) {
        this.devisService = devisService;
    }
    @PostMapping("/approve/{devisId}")
    public ResponseEntity<ContratDTO> approveDevis(@PathVariable Long devisId) {
        System.out.println("hello here");
        try {
            ContratDTO contratDTO = devisService.approveDevis(devisId);
            return new ResponseEntity<>(contratDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
