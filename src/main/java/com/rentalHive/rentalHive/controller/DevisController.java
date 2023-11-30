package com.rentalHive.rentalHive.controller;

import com.rentalHive.rentalHive.model.dto.CustomResponse;
import com.rentalHive.rentalHive.model.dto.DevisDTO;
import com.rentalHive.rentalHive.model.entities.Demande;
import com.rentalHive.rentalHive.repository.IDemandeRepo;
import com.rentalHive.rentalHive.service.IDevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rental")
public class DevisController {
    private final IDevisService devisService;
    private final IDemandeRepo demandeRepo;
    @Autowired
    public DevisController(@Qualifier("devisServiceImpl") IDevisService devisService ,
                           IDemandeRepo demandeRepo) {
        this.devisService = devisService;
        this.demandeRepo = demandeRepo;
    }

    @GetMapping("")
        public String check(){
            return "checked";
        }

    @GetMapping("/demandes/{demande_id}/devis")
    public ResponseEntity<CustomResponse<List<DevisDTO>>> getAllDevis(){
            CustomResponse<List<DevisDTO>> response = new CustomResponse<>("List of devis", devisService.findAll());
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/demandes/{demande_id}/devis")
    public ResponseEntity<CustomResponse<DevisDTO>> generateDevis(@PathVariable long demande_id)
    {
        Optional<Demande> demande = demandeRepo.findById(demande_id);
        if(demande.isPresent()){
            DevisDTO devisDTO = devisService.generateDevis(demande.get());
            CustomResponse<DevisDTO> response = new CustomResponse<>("data", devisDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }else {
            CustomResponse<DevisDTO> response = new CustomResponse<>("There is no such demande with the id :"+demande_id, null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }



}

