package com.rentalHive.rentalHive.controller;

import com.rentalHive.rentalHive.model.dto.DemandeDTO;
import com.rentalHive.rentalHive.service.IDemandeService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/create/{userId}/{equipmentIds}")
    public ResponseEntity<String> createDemande(@RequestBody DemandeDTO demandeDTO,
                                                @PathVariable int userId,
                                                @PathVariable List<Long> equipmentIds) {
        return demandeService.createDemande(demandeDTO, equipmentIds, userId);
    }
}
