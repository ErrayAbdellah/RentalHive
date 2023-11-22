package com.rentalHive.rentalHive.service.implementations;

import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.model.entities.RentalRecord;
import com.rentalHive.rentalHive.model.entities.enums.Status;
import com.rentalHive.rentalHive.repository.IEquipmentRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class EquipmentServiceImplTest {

    @Mock
    private IEquipmentRepo equipmentRepository;

    @InjectMocks
    private EquipmentServiceImpl equipmentService;

    @Test
    public void testFindEquipmentByName() {
        String equipmentName = "SampleEquipment";
        List<RentalRecord> rentalRecords = new ArrayList<>();
        Equipment sampleEquipment = new Equipment(1L, equipmentName, 100.0, 5, Status.AVAILABLE,rentalRecords);
        Optional<Equipment> optionalEquipment = Optional.of(sampleEquipment);

        when(equipmentRepository.findByName(equipmentName)).thenReturn(optionalEquipment);

        Optional<EquipmentDTO> result = equipmentService.findEquipmentByName(equipmentName);

        assertEquals(optionalEquipment.map(equipment ->
                new EquipmentDTO(
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

    @Test
    public void EquipmentService_CreateEquipment_ReturnsEquipmentDTO(){
        Equipment equipmentToCreate = Equipment.builder()
                .quantity(2)
                .name("engine n°3")
                .price(200)
                .build();
        when(equipmentRepository.save(Mockito.any(Equipment.class))).thenReturn(equipmentToCreate);

        EquipmentDTO equipmentDTO = EquipmentDTO.builder()
                .quantity(2)
                .name("engine n°3")
                .price(200)
                .build();
        EquipmentDTO savedEquipmentDTO = equipmentService.createEquipment(equipmentDTO);
        Assertions.assertThat(savedEquipmentDTO).isNotNull();
    }
}