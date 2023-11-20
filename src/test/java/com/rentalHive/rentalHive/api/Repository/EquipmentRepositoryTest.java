package com.rentalHive.rentalHive.api.Repository;

import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.model.entities.enums.Status;
import com.rentalHive.rentalHive.repository.EquipmentRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EquipmentRepositoryTest {

    private EquipmentRepo equipmentRepo;
    @Autowired
    public EquipmentRepositoryTest(EquipmentRepo equipmentRepo) {
        this.equipmentRepo = equipmentRepo;
    }



    @Test
    public void EquipmentRepo_SaveEquipment_ReturnSavedEquipment(){
        //Arrange
        Equipment equipment = Equipment.builder()
                .price(106.5)
                .status(Status.AVAILABLE)
                .name("Hitachi Dump Truck")
                .quantity(2)
                .build();

        // Act
        Equipment savedEquipment = equipmentRepo.save(equipment);

        //Test
        Assertions.assertThat(savedEquipment).isNotNull();
        Assertions.assertThat(savedEquipment.getEquipmentId()).isGreaterThan(0);
    }

    @Test
    public void EquipmentRepo_FindByStatus_ReturnsNotEmpty(){
        //Arrange
        Equipment equipment = Equipment.builder()
                .price(200)
                .quantity(1)
                .status(Status.AVAILABLE)
                .name("Engine n° 2")
                .build();
        equipmentRepo.save(equipment);
        //Act
        List<Equipment> equipmentList = equipmentRepo.findByStatus(Status.AVAILABLE);
        // Test
        Assertions.assertThat(equipmentList).isNotEmpty();
    }
}
