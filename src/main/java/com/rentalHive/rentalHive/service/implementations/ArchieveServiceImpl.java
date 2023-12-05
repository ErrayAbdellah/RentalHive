package com.rentalHive.rentalHive.service.implementations;

import com.rentalHive.rentalHive.model.entities.Archive;
import com.rentalHive.rentalHive.model.entities.Demande;
import com.rentalHive.rentalHive.repository.IArchieveRepo;
import com.rentalHive.rentalHive.service.IArchieveService;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Optional;

@Service
public class ArchieveServiceImpl  implements IArchieveService {

    private final IArchieveRepo archiveRepository;

    @Autowired
    @Lazy
    private GenericEntityService genericEntityService;

    @Autowired
    public ArchieveServiceImpl(IArchieveRepo archiveRepository) {
        this.archiveRepository = archiveRepository;
    }



    @Override
    public void archiveEntity(Optional<?> entity, String archivedBy, String archivedReason) {
        entity.ifPresent(e -> {
            Archive archive = new Archive();
            archive.setRelatedEntityId(getEntityId(e));
            archive.setArchivedDate(new Date());
            archive.setArchivedBy(archivedBy);
            archive.setArchivedReason(archivedReason);
            archive.setRelatedEntityType(e.getClass().getSimpleName());
            archiveRepository.save(archive);
        });
    }


    private Long getEntityId(Object entity) {
        try {
            Method getIdMethod = entity.getClass().getMethod("getId");
            return (Long) getIdMethod.invoke(entity);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to get entity ID", e);
        }
    }

    @Override
    public Optional<?> getEntityByTypeAndId(String entityType, Long id) {
        switch (entityType.toLowerCase()) {
            case "devis":
                return genericEntityService.getDevisById(id);
            case "contract":
                return genericEntityService.getContractById(id);
            case "demande":
                return genericEntityService.getDemandeById(id);
            default:
                throw new IllegalArgumentException("Unsupported entity type: " + entityType);
        }
    }
    }