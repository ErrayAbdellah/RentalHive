package com.rentalHive.rentalHive.repository;

import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.model.entities.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IEquipmentRepo extends JpaRepository<Equipment, Long> {
    Optional<Equipment> findByName(String name);
    List<Equipment> findAll();
    Optional<Equipment> findById(long id);
    boolean existsByName(String testEquipment);
}
