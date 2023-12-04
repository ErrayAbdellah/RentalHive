package com.rentalHive.rentalHive.service.implementations;

import com.rentalHive.rentalHive.repository.IEquipmentRepo;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EquipmentServiceImplTest {
//
//    @Mock
//    private EquipmentRepo equipmentRepository;
//
//    @InjectMocks
//    private EquipmentServiceImpl equipmentService;
//
//    @Test
//    public void testFindEquipmentByName() {
//        String equipmentName = "SampleEquipment";
//        List<RentalRecord> rentalRecords = new ArrayList<>();
//        Equipment sampleEquipment = new Equipment(1L, equipmentName, 100.0, 5, Status.AVAILABLE,rentalRecords);
//        Optional<Equipment> optionalEquipment = Optional.of(sampleEquipment);
//
//        when(equipmentRepository.findByName(equipmentName)).thenReturn(optionalEquipment);
//
//        Optional<EquipmentDTO> result = equipmentService.findEquipmentByName(equipmentName);
//
//        assertEquals(optionalEquipment.map(equipment ->
//                new EquipmentDTO(
//                        equipment.getName(),
//                        equipment.getPrice(),
//                        equipment.getQuantity(),
//                        equipment.getStatus()
//                )
//        ), result);
//    }
//
//    @Test
//    public void testFindEquipmentByNameNotFound() {
//        String equipmentName = "NonExistentEquipment";
//        Optional<Equipment>optionalEquipment = Optional.empty();
//        when(equipmentRepository.findByName(equipmentName)).thenReturn(optionalEquipment);
//
//        Optional<EquipmentDTO> result = equipmentService.findEquipmentByName(equipmentName);
//
//        assertEquals(optionalEquipment.map(equipment ->
//                new EquipmentDTO(
//                        equipment.getName(),
//                        equipment.getPrice(),
//                        equipment.getQuantity(),
//                        equipment.getStatus()
//                )
//        ), result);
//
//    }
//
//    @Test
//    public void testFindEquipmentByStatus() {
//        Status status = Status.AVAILABLE;
//
//        List<EquipmentDTO> equipmentDTOList = equipmentService.findEquipmentByStatus(status);
//
//        assertNotNull(equipmentDTOList);
//    }
}