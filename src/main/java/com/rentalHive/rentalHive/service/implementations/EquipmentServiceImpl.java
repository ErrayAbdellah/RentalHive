package com.rentalHive.rentalHive.service.implementations;

import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.repository.EquipementRepo;
import com.rentalHive.rentalHive.repository.IEquipmentRepo;
import com.rentalHive.rentalHive.service.IEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
                        equipment.getEquipmentId(),
                        equipment.getName(),
                        equipment.getPrice(),
                        equipment.getQuantity(),
                        equipment.getStatus()
                )
        );
    }
}
