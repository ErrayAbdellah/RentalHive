package com.rentalHive.rentalHive.service.implementations;

import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.enums.Category;
import com.rentalHive.rentalHive.repository.IEquipmentRepo;
import com.rentalHive.rentalHive.service.IEquipmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Primary
public class EquipmentServiceImpl implements IEquipmentService {

    private final IEquipmentRepo equipmentRepo ;
    private final String uploadDirectory = "C:\\Users\\Youcode\\Desktop\\Java\\rentalHive_finalVersion\\images1";

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

    @Override
    public ResponseEntity<String> createEquipment(EquipmentDTO equipmentDTO, MultipartFile imageFile) throws IOException {
        Equipment equipment = Equipment.ToEquipment(equipmentDTO);

        if (imageFile.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload");
        }

        File directory = new File(uploadDirectory);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                return ResponseEntity.badRequest().body("Failed to create the destination directory");
            }
        }

        File destinationFile = new File(directory, imageFile.getOriginalFilename());
        imageFile.transferTo(destinationFile);
        equipment.setImage(directory.getPath()+imageFile.getOriginalFilename());
        equipmentRepo.save(equipment);

        return ResponseEntity.ok("Record is successfully created");
    }


    @Override
    public ResponseEntity<List<Equipment>> getAllEquipment() {
        List<Equipment> equipment = equipmentRepo.findAll();
        return ResponseEntity.ok(equipment);
    }

    @Override
    public ResponseEntity<String>  updateEquipment(long equipmentId, EquipmentDTO equipmentDTO) {
        Optional<Equipment> optionalEquipment = equipmentRepo.findById(equipmentId);

        if (optionalEquipment.isPresent()) {
            Equipment existingEquipment = optionalEquipment.get();
            existingEquipment.setName(equipmentDTO.getName());
            existingEquipment.setPrice(equipmentDTO.getPrice());
            equipmentRepo.save(existingEquipment);
            return ResponseEntity.ok("Equipment updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
