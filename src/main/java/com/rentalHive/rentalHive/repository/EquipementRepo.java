package com.rentalHive.rentalHive.repository;

import com.rentalHive.rentalHive.model.entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EquipementRepo extends JpaRepository<Equipment,Integer>
{
    Optional<Equipment> findByName(String name);
    List<Equipment> findByStatus(String status);
    List<Equipment> findByQuantityGreaterThan(int quantity);
    List<Equipment> findAll();

}
