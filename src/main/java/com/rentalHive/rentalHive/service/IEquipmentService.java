package com.rentalHive.rentalHive.service;

import com.rentalHive.rentalHive.enums.Category;
import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface IEquipmentService {
    ResponseEntity<EquipmentDTO> findEquipmentByName(String name);
    ResponseEntity<List<EquipmentDTO>> findEquipmentByType(Category type);
    ResponseEntity createEquipment(EquipmentDTO equipmentDTO, MultipartFile imageFile) throws IOException;
    ResponseEntity<List<Equipment>> getAllEquipment();
    ResponseEntity<String> updateEquipment(long equipmentId, EquipmentDTO equipmentDTO);
}
