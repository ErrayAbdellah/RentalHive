package com.rentalHive.rentalHive.service;

import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.model.entities.enums.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
public interface IEquipmentService {
    ResponseEntity<EquipmentDTO> findEquipmentByName(String name);
    ResponseEntity<List<EquipmentDTO>> findEquipmentByType(Category type);
    ResponseEntity createEquipment(EquipmentDTO equipmentDTO, MultipartFile file) throws IOException, SQLException;
    ResponseEntity<List<Equipment>> getAllEquipment();
    public ResponseEntity<String> updateEquipment(long id ,EquipmentDTO equipmentDTO);
}
