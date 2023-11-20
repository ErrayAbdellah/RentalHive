package com.rentalHive.rentalHive.service.implementations;

import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.model.entities.enums.Status;
import com.rentalHive.rentalHive.repository.EquipementRepo;
import com.rentalHive.rentalHive.repository.
import com.rentalHive.rentalHive.service.IEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EquipmentServiceImpl implements IEquipmentService {
    private final EquipementRepo equipmentRepository;

    @Autowired
    public EquipmentServiceImpl(EquipementRepo equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public Optional<EquipmentDTO> findEquipmentByName(String name) {
        Optional<Equipment> equipmentOptional = equipmentRepository.findByName(name);

        return equipmentOptional.map(equipment ->
                new EquipmentDTO(
                        equipment.getName(),
                        equipment.getPrice(),
                        equipment.getQuantity(),
                        equipment.getStatus()
                )
        );
    }

    @Override
    public List<EquipmentDTO> findEquipmentByStatus(Status status) {
        List<Equipment> equipmentList = equipmentRepository.findByStatus(status);

        if (!equipmentList.isEmpty()) {
            return equipmentList.stream().map(equipment ->
                    new EquipmentDTO(
                            equipment.getName(),
                            equipment.getPrice(),
                            equipment.getQuantity(),
                            equipment.getStatus()
                    )
            ).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}
