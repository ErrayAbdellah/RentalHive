package com.rentalHive.rentalHive.service.implementations;

import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.model.entities.Image;
import com.rentalHive.rentalHive.model.entities.enums.Category;
import com.rentalHive.rentalHive.repository.IEquipmentRepo;
import com.rentalHive.rentalHive.repository.IImageRepo;
import com.rentalHive.rentalHive.service.IEquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EquipmentServiceImpl implements IEquipmentService {

    private final IEquipmentRepo equipmentRepo ;
    private final IImageRepo imageRepo ;
    @Override
    public ResponseEntity<EquipmentDTO> findEquipmentByName(String name) {
        Optional<EquipmentDTO> equipmentDTOOptional = equipmentRepo.findEquipmentByName(name);
        return equipmentDTOOptional
                .map(equipmentDTO -> new ResponseEntity<>(equipmentDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<List<EquipmentDTO>> findEquipmentByType(Category type) {
        List<EquipmentDTO> equipmentList = equipmentRepo.findEquipmentByCategory(type);

        if (!equipmentList.isEmpty()) {
            return new ResponseEntity<>(equipmentList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @Override
//    public ResponseEntity createEquipment(EquipmentDTO equipmentDTO, MultipartFile file) throws IOException, SQLException {
//
//        Equipment equipment = Equipment.ToEquipment(equipmentDTO);
//        if (file.isEmpty()) {
//            return ResponseEntity.badRequest().body("Please select a file to upload");
//        }
//        byte[] bytes = file.getBytes();
//        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
//
//        Image image = new Image();
//        image.setImage(blob);
////        image.setEquipment(e);
//        imageRepo.save(image);
//        equipmentRepo.save(equipment);
//        return ResponseEntity.ok("record is successfully");
//
//    }

@Override
public ResponseEntity createEquipment(EquipmentDTO equipmentDTO, MultipartFile file) throws IOException, SQLException {

    Equipment equipment = Equipment.ToEquipment(equipmentDTO);
    equipmentRepo.save(equipment);

    if (file.isEmpty()) {
        return ResponseEntity.badRequest().body("Please select a file to upload");
    }

    byte[] bytes = file.getBytes();
    Blob blob = new SerialBlob(bytes);

    Image image = new Image();
    image.setImage(blobToBytes(blob));
    image.setEquipment(equipment);  // Set the Equipment entity

    imageRepo.save(image);

    return ResponseEntity.ok("Record is successfully created");
}
    private byte[] blobToBytes(Blob blob) throws SQLException, IOException {
        try (InputStream inputStream = blob.getBinaryStream()) {
            return inputStream.readAllBytes();
        }
    }



    @Override
    public ResponseEntity<List<Equipment>> getAllEquipment() {
        List<Equipment> equipment = equipmentRepo.findAll();
        return ResponseEntity.ok(equipment);
    }

    @Override
    public ResponseEntity<String> updateEquipment(long equipmentId ,EquipmentDTO equipmentDTO) {
        Optional<Equipment> optionalEquipment = equipmentRepo.findById(equipmentId);

        if (optionalEquipment.isPresent()) {
            Equipment existingEquipment = optionalEquipment.get();
            existingEquipment.setName(equipmentDTO.getName());
//            existingEquipment.setPrice(equipmentDTO.getPrice());
            equipmentRepo.save(existingEquipment);
            return ResponseEntity.ok("Equipment updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
