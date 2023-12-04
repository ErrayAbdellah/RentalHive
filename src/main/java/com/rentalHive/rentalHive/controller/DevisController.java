package com.rentalHive.rentalHive.controller;

import com.rentalHive.rentalHive.model.dto.ContratDTO;
import com.rentalHive.rentalHive.model.entities.Devis;
import com.rentalHive.rentalHive.repository.IDevisRepo;
import com.rentalHive.rentalHive.service.IDevisService;
import com.rentalHive.rentalHive.service.implementations.DevisServiceImpl;
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
    private final DevisServiceImpl devisService;


    @Autowired
    public DevisController(DevisServiceImpl devisService) {
        this.devisService = devisService;
    }
    @PostMapping("/approve/{devisId}")
    public ResponseEntity<String> approveDevis(@PathVariable Long devisId) {
        try {
            String approvalResult = devisService.approveDevis(devisId);
            return ResponseEntity.ok(approvalResult);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error: " + e.getMessage());
        }
    }
}
