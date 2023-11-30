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
    public ResponseEntity<String> approveDevis(@PathVariable Long devisId) {
        try {
            ContratDTO contratDTO = devisService.approveDevis(devisId);
            if (contratDTO != null) {
                return ResponseEntity.ok("Devis approved and contract created successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Devis not found or error occurred.");
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error.");
        }
    }
}
