package com.rentalHive.rentalHive.repository;

import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.Equipment;
import com.rentalHive.rentalHive.model.entities.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IEquipmentRepo extends JpaRepository<Equipment, Long> {
    List<Equipment> findAll();
    Optional<Equipment> findById(long id);
    Optional<EquipmentDTO> findEquipmentByName(String name);
    List<EquipmentDTO> findEquipmentByCategory(Category status);
}
