package com.rentalHive.rentalHive.controller;

import  com.rentalHive.rentalHive.controller.EquipmentController;
import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.enums.Status;
import com.rentalHive.rentalHive.repository.EquipementRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class EquipementControllerTest {

    @Mock
    private EquipementRepo equipementRepo;

    @InjectMocks
    private EquipmentController equipmentController;

    @Test
    public void test_create_new_equipment_object() {
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        equipmentDTO.setName("Test Equipment");
        equipmentDTO.setPrice(100.0);
        equipmentDTO.setQuantity(5);
        equipmentDTO.setStatus(Status.AVAILABLE);

        ResponseEntity<String> response = equipmentController.createEquipement(equipmentDTO);
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody().equals("Equipement created succesfully");
    }
    @Test
    public void test_save_equipment_to_repo() {
        EquipmentDTO equipmentDTO = new EquipmentDTO();

        equipmentDTO.setName("Test Equipment");
        equipmentDTO.setPrice(100.0);
        equipmentDTO.setQuantity(5);
        equipmentDTO.setStatus(Status.AVAILABLE);
        equipmentController.createEquipement(equipmentDTO);
        assert equipementRepo.existsByName("Test Equipment");
    }
}