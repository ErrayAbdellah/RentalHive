package com.rentalHive.rentalHive.service;

import com.rentalHive.rentalHive.enums.Type;
import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IEquipmentService {
    ResponseEntity<EquipmentDTO> findEquipmentByName(String name);
    ResponseEntity<List<EquipmentDTO>> findEquipmentByType(Type type);
    ResponseEntity createEquipment(EquipmentDTO equipmentDTO);
    ResponseEntity<List<Equipment>> getAllEquipment();
    public ResponseEntity<String> updateEquipment(long id ,EquipmentDTO equipmentDTO);
}
