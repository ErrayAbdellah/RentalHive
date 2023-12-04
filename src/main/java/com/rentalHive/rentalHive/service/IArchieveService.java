package com.rentalHive.rentalHive.service;

import com.rentalHive.rentalHive.model.entities.Demande;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IArchieveService  {

    void archiveEntity(Optional<?> entity, String archivedBy, String archivedReason);
    Optional<?> getEntityByTypeAndId(String entityType, Long id);
}
