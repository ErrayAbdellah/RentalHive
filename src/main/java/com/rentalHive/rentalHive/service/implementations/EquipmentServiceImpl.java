package com.rentalHive.rentalHive.service.implementations;

import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.model.entities.enums.Status;

import com.rentalHive.rentalHive.repository.IEquipmentRepo;
import com.rentalHive.rentalHive.service.IEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EquipmentServiceImpl implements IEquipmentService {
    private final IEquipmentRepo equipmentRepo;

    @Autowired
    public EquipmentServiceImpl(IEquipmentRepo equipmentRepo) {
        this.equipmentRepo = equipmentRepo;
    }

    @Override
    public Optional<EquipmentDTO> findEquipmentByName(String name) {
        Optional<Equipment> equipmentOptional = equipmentRepo.findByName(name);

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

    @Override
    public List<EquipmentDTO> findEquipmentByStatus(Status status) {
        List<Equipment> equipmentList = equipmentRepo.findByStatus(status);

        if (!equipmentList.isEmpty()) {
            return equipmentList.stream().map(equipment ->
                    new EquipmentDTO(
                            equipment.getEquipmentId(),
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

    @Override
    public EquipmentDTO createEquipment(EquipmentDTO equipmentDTO) {
        validateEquipmentDTO(equipmentDTO);
        Equipment equipment = Equipment.builder()
                .name(equipmentDTO.getName())
                .price(equipmentDTO.getPrice())
                .quantity(equipmentDTO.getQuantity())
                .status(equipmentDTO.getStatus())
                .build();
        Equipment createdEquipment = equipmentRepo.save(equipment);
        if (createdEquipment != null) {
            return convertToDTO(createdEquipment);
        } else {
            throw new IllegalStateException("Failed to save equipment. Returned object is null.");
        }
    }

    @Override
    public ResponseEntity<List<Equipment>> getAllEquipment() {
        List<Equipment> equipment = equipmentRepo.findAll();
        if (equipment.isEmpty()){
            return null;
        }
        return ResponseEntity.ok(equipment);
    }

    //Helper functions
    private void validatePriceAndQuantity(double price, int quantity) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (quantity == 0) {
            throw new IllegalArgumentException("Quantity cannot be less than 1");
        }
    }

    private void validateEquipmentDTO(EquipmentDTO equipmentDTO) {
        if (equipmentDTO.getName() == null || equipmentDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("Equipment name cannot be empty");
        }

        validatePriceAndQuantity(equipmentDTO.getPrice(), equipmentDTO.getQuantity());
    }
    private EquipmentDTO convertToDTO(Equipment equipment) {
        return  EquipmentDTO.builder()
                .quantity(equipment.getQuantity())
                .price(equipment.getPrice())
                .status(equipment.getStatus())
                .name(equipment.getName())
                .build();
//        return equipmentDTO;
    }

    public ResponseEntity<String> updateEquipment(EquipmentDTO equipmentDTO) {
        Optional<Equipment> optionalEquipment = equipmentRepo.findById(equipmentDTO.getEquipmentId());
        if (optionalEquipment.isPresent()) {
            Equipment existingEquipment = Equipment.toEquipment(equipmentDTO);
            equipmentRepo.save(existingEquipment);
            return ResponseEntity.ok("Equipment updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
