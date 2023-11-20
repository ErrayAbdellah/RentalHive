package com.rentalHive.rentalHive.controller;

import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.repository.EquipmentRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class EquipementControllerTest {

    @Mock
    private EquipmentRepo equipmentRepo;

    @InjectMocks
    private EquipmentController equipmentController;

    @Test
    void testUpdateEquipment() {
        Long equipmentId = 1L;
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        equipmentDTO.setName("Updated Equipment");
        equipmentDTO.setPrice(150.0);
        equipmentDTO.setQuantity(10);

        Equipment existingEquipment = new Equipment();
        existingEquipment.setEquipmentId(equipmentId);
        existingEquipment.setName("Existing Equipment");
        existingEquipment.setPrice(100.0);
        existingEquipment.setQuantity(5);

        when(equipmentRepo.findById((long) Math.toIntExact(equipmentId))).thenReturn(Optional.of(existingEquipment));

        ResponseEntity<String> responseEntity = equipmentController.updateEquipment(equipmentId, equipmentDTO);

        verify(equipmentRepo, times(1)).findById((long) Math.toIntExact(equipmentId));
        verify(equipmentRepo, times(1)).save(existingEquipment);
        assertNotNull(responseEntity);
        assertEquals("Equipment updated successfully.", responseEntity.getBody());
    }
}