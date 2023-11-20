package com.rentalHive.rentalHive.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.repository.EquipmentRepo;
import com.rentalHive.rentalHive.service.implementations.EquipmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = EquipmentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class EquipementControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EquipmentServiceImpl equipmentService;
    @Autowired
    private ObjectMapper objectMapper;
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

    @Test
    public void EquipmentController_CreateEquipment_ReturnCreated() throws Exception{
        EquipmentDTO equipmentDTO = EquipmentDTO.builder()
                        .quantity(2)
                        .price(50)
                        .name("ENGINE N° 100")
                        .build();
        BDDMockito.given(equipmentService.createEquipment(ArgumentMatchers.any())).
                                                        willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        ResultActions response = mockMvc.perform(post("/api/equipment").
                contentType(MediaType.APPLICATION_JSON).contentType(objectMapper.writeValueAsString(equipmentDTO)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }
}