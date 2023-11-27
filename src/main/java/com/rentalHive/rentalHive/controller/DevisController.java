package com.rentalHive.rentalHive.controller;

import com.rentalHive.rentalHive.model.dto.CustomResponse;
import com.rentalHive.rentalHive.model.dto.DevisDTO;
import com.rentalHive.rentalHive.service.IDevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rental")
public class DevisController {
    private final IDevisService devisService;
    @Autowired
    public DevisController(@Qualifier("devisServiceImpl") IDevisService devisService) {
        this.devisService = devisService;
    }

    @GetMapping("/demandes/{demande_id}/devies")
    public ResponseEntity<List<DevisDTO>> getAllDevis(){
        List<DevisDTO> devisDTOList = devisService.findAll();
        return ResponseEntity.ok(devisDTOList);
    }
}

