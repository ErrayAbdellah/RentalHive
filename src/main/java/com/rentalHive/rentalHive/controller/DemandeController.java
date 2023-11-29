package com.rentalHive.rentalHive.controller;

import com.rentalHive.rentalHive.model.dto.DemandeDTO;
import com.rentalHive.rentalHive.model.entities.Demande;
import com.rentalHive.rentalHive.service.IDemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demandes")
public class DemandeController {
    private final IDemandeService demandeService;
    @Autowired
    public DemandeController(IDemandeService demandeService) {
        this.demandeService = demandeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Demande>> getAllDemandes() {
        return demandeService.getAllDemandes();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createDemande(@RequestBody DemandeDTO demandeDTO) {
        return demandeService.createDemande(demandeDTO);
    }
}
