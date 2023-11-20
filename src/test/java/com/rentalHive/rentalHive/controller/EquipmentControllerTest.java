package com.rentalHive.rentalHive.controller;

import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.repository.EquipmentRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class EquipmentControllerTest {



    @InjectMocks
    private EquipmentController equipmentController;

    @Mock
    private EquipmentRepo equipmentRepoMock;

    @Test
    public void testUpdateEquipmentSuccessfully() {
        long equipmentId = 1;
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        equipmentDTO.setName("Updated Equipment");

        Equipment existingEquipment = new Equipment();
        existingEquipment.setEquipmentId(equipmentId);
        System.out.println(equipmentRepoMock.findById(equipmentId));
        when(equipmentRepoMock.findById(equipmentId)).thenReturn(Optional.of(existingEquipment));
        when(equipmentRepoMock.save(any(Equipment.class))).thenReturn(existingEquipment);

        ResponseEntity<String> responseEntity = equipmentController.updateEquipment(equipmentId, equipmentDTO);

        System.out.println("Response Status: " + responseEntity.getStatusCode());
        System.out.println("Existing Equipment Name: " + existingEquipment.getName());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Updated Equipment", existingEquipment.getName());

        verify(equipmentRepoMock, times(1)).findById(equipmentId);
        verify(equipmentRepoMock, times(1)).save(existingEquipment);
    }

    @Test
    public void testUpdateEquipmentWithNullDTO() {
        long equipmentId = 10;

        ResponseEntity<String> responseEntity = equipmentController.updateEquipment(equipmentId, null);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        verify(equipmentRepoMock, never()).findById(anyLong());
        verify(equipmentRepoMock, never()).save(any(Equipment.class));
    }
}