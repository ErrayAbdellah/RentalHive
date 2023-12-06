package com.rentalHive.rentalHive.controller;

import com.rentalHive.rentalHive.model.dto.ArchieveDTO;
import com.rentalHive.rentalHive.model.entities.Demande;
import com.rentalHive.rentalHive.service.ArchivableEntity;
import com.rentalHive.rentalHive.service.IArchieveService;
import com.rentalHive.rentalHive.service.implementations.ArchieveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/Archieve")
public class ArchieveController {
    private final IArchieveService archiveService;

    @Autowired
    public ArchieveController(IArchieveService archiveService) {
        this.archiveService = archiveService;
    }


    @PostMapping("/{entityType}/{id}")
    public ResponseEntity<ArchieveDTO> archiveEntity(
            @PathVariable String entityType,
            @PathVariable Long id,
            @RequestParam String archivedBy,
            @RequestParam String archivedReason
    ) {

        try {
            System.out.println("Reached archiveEntity method");
            Optional<?> entity = archiveService.getEntityByTypeAndId(entityType, id);

            archiveService.archiveEntity(entity, archivedBy, archivedReason);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
