package com.rentalHive.rentalHive.service.implementations;

import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.model.entities.enums.Status;
import com.rentalHive.rentalHive.repository.EquipementRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EquipmentServiceImplTest {

    @Mock
    private EquipementRepo equipmentRepository;

    @InjectMocks
    private EquipmentServiceImpl equipmentService;

    @Test
    public void testFindEquipmentByName() {
        String equipmentName = "SampleEquipment";
        Equipment sampleEquipment = new Equipment(1L, equipmentName, 100.0, 5, Status.AVAILABLE);
        Optional<Equipment> optionalEquipment = Optional.of(sampleEquipment);

        when(equipmentRepository.findByName(equipmentName)).thenReturn(optionalEquipment);

        Optional<EquipmentDTO> result = equipmentService.findEquipmentByName(equipmentName);

        assertEquals(optionalEquipment.map(equipment ->
                new EquipmentDTO(
                        equipment.getEquipmentId(),
                        equipment.getName(),
                        equipment.getPrice(),
                        equipment.getQuantity(),
                        equipment.getStatus()
                )
        ), result);
    }

    @Test
    public void testFindEquipmentByNameNotFound() {
        String equipmentName = "NonExistentEquipment";

        Optional<Equipment>optionalEquipment = Optional.empty();

        when(equipmentRepository.findByName(equipmentName)).thenReturn(optionalEquipment);

        Optional<EquipmentDTO> result = equipmentService.findEquipmentByName(equipmentName);

        assertEquals(optionalEquipment.map(equipment ->
                new EquipmentDTO(
                    equipment.getEquipmentId(),
                    equipment.getName(),
                    equipment.getPrice(),
                    equipment.getQuantity(),
                    equipment.getStatus()
                )
        ), result);

    }

    @Test
    public void testFindEquipmentByStatus() {
        Status status = Status.AVAILABLE;

        List<EquipmentDTO> equipmentDTOList = equipmentService.findEquipmentByStatus(status);

        assertNotNull(equipmentDTOList);
    }
}
