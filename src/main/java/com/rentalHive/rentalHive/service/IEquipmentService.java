package com.rentalHive.rentalHive.service;

import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.enums.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IEquipmentService {
    Optional<EquipmentDTO> findEquipmentByName(String name);
    List<EquipmentDTO> findEquipmentByStatus(Status status);
    EquipmentDTO createEquipment(EquipmentDTO equipmentDTO);
    ResponseEntity<List<Equipment>> getAllEquipment();
    public ResponseEntity<String> updateEquipment(EquipmentDTO equipmentDTO);

}
