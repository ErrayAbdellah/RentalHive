package com.rentalHive.rentalHive.api.Repository;

import com.rentalHive.rentalHive.repository.IEquipmentRepo;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EquipmentRepositoryTest {

//    private EquipmentRepo equipmentRepo;
//    @Autowired
//    public EquipmentRepositoryTest(EquipmentRepo equipmentRepo) {
//        this.equipmentRepo = equipmentRepo;
//    }
//
//
//
////    @Test
////    public void EquipmentRepo_SaveEquipment_ReturnSavedEquipment(){
////        Equipment equipment = Equipment.builder()
////                .price(106.5)
////                .status(Status.AVAILABLE)
////                .name("Hitachi Dump Truck")
////                .quantity(2)
////                .build();
//
//
//        Equipment savedEquipment = equipmentRepo.save(equipment);
//
//
//        Assertions.assertThat(savedEquipment).isNotNull();
//        Assertions.assertThat(savedEquipment.getEquipmentId()).isGreaterThan(0);
//    }
//
//    @Test
//    public void EquipmentRepo_FindByStatus_ReturnsNotEmpty(){
//        //Arrange
//        Equipment equipment = Equipment.builder()
//                .price(200)
//                .quantity(1)
//                .status(Status.AVAILABLE)
//                .name("Engine n° 2")
//                .build();
//        equipmentRepo.save(equipment);
//        List<Equipment> equipmentList = equipmentRepo.findByStatus(Status.AVAILABLE);
//        Assertions.assertThat(equipmentList).isNotEmpty();
//    }
}
