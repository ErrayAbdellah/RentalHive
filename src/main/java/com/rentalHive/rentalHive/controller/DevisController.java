package com.rentalHive.rentalHive.controller;


import com.rentalHive.rentalHive.model.ConditionDTO;
import com.rentalHive.rentalHive.service.IDevisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rentalHive.rentalHive.enums.devisStatus;
import com.rentalHive.rentalHive.model.dto.CustomResponse;
import com.rentalHive.rentalHive.model.dto.DevisDTO;
import com.rentalHive.rentalHive.model.entities.Demande;
import com.rentalHive.rentalHive.repository.IDemandeRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@RestController
@RequestMapping("/api/devis")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class DevisController {
//    private final DevisServiceImpl devisService;
    private final IDevisService devisService;
    private final IDemandeRepo demandeRepo;
    @PostMapping("/approve/{devisId}")
    public ResponseEntity<String> approveDevis(@PathVariable Long devisId , @RequestBody List<ConditionDTO> conditionDTOList) {
        try {
            String approvalResult = devisService.approveDevis(devisId, conditionDTOList);
            return ResponseEntity.ok(approvalResult);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error: " + e.getMessage());
        }
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
        if (devisService.isExists(demande_id)){
            CustomResponse<DevisDTO> response = new CustomResponse<>("You cannot create another devis for this demande", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if(demande.isPresent()){
            DevisDTO devisDTO = devisService.generateDevis(demande.get());
            CustomResponse<DevisDTO> response = new CustomResponse<>("data", devisDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }else {
            CustomResponse<DevisDTO> response = new CustomResponse<>("There is no such demande with the id :"+demande_id, null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/demandes/{demande_id}/devis/accept")
    public ResponseEntity<CustomResponse<DevisDTO>> acceptDevis(@PathVariable long demande_id){
        try{
            DevisDTO patchedDevisDTO = devisService.PatchDevisState(demande_id, devisStatus.APPROVED);
            CustomResponse<DevisDTO> response = new CustomResponse<>("The Devis has been successfully approved", patchedDevisDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (NoSuchElementException e){
            CustomResponse response = new CustomResponse("No such devis for this demande  ", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/demandes/{demande_id}/devis/decline")
    public ResponseEntity<CustomResponse<DevisDTO>> declineDevis(@PathVariable long demande_id){
        try{
            DevisDTO patchedDevisDTO = devisService.PatchDevisState(demande_id, devisStatus.DECLINED);
            CustomResponse<DevisDTO> response = new CustomResponse<>("The Devis has been successfully declined", patchedDevisDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (NoSuchElementException e){
            CustomResponse response = new CustomResponse("No such devis for this demande  ", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/demandes/{demande_id}/devis/exists")
    public ResponseEntity<Boolean> isDevisExists(@PathVariable long demande_id){
        if (devisService.isExists(demande_id)){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }
}

