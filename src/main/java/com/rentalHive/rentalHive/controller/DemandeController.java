package com.rentalHive.rentalHive.controller;

import com.rentalHive.rentalHive.enums.State;
import com.rentalHive.rentalHive.model.dto.DemandeDTO;
import com.rentalHive.rentalHive.model.dto.DemandeDTOforDevis;
import com.rentalHive.rentalHive.model.entities.Demande;
import com.rentalHive.rentalHive.service.IDemandeService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demandes")
@CrossOrigin(origins = "http://localhost:4200")

public class DemandeController {
    private final IDemandeService demandeService;
    @Autowired
    public DemandeController(IDemandeService demandeService) {
        this.demandeService = demandeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DemandeDTOforDevis>> getAllDemandes(@RequestParam(required = false)State state) {
        return demandeService.getAllDemandes(state);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createDemande(@Valid @RequestBody DemandeDTO demandeDTO) {
        return demandeService.createDemande(demandeDTO);
    }
}
