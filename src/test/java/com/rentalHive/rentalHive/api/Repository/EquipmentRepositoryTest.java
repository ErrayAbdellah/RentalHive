package com.rentalHive.rentalHive.api.Repository;

import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.model.entities.enums.Status;
import com.rentalHive.rentalHive.repository.EquipementRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class EquipmentRepositoryTest {

    private EquipementRepo equipementRepo;
    @Autowired
    public EquipmentRepositoryTest(EquipementRepo equipementRepo) {
        this.equipementRepo = equipementRepo;
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
        Equipment savedEquipment = equipementRepo.save(equipment);

        //Test
        Assertions.assertThat(savedEquipment).isNotNull();
        Assertions.assertThat(savedEquipment.getEquipmentId()).isGreaterThan(0);
    }

    @Test
    public void EquipmentRepo_checkEquipmentExistence_returnTrueOrFalse(){
        boolean exists = equipementRepo.existsById(100);
        Assertions.assertThat(exists).isFalse();
    }
}
