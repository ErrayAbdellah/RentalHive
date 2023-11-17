package com.rentalHive.rentalHive.repository;

import com.rentalHive.rentalHive.model.entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface IEquipmentRepo extends JpaRepository<Equipment,Long> {
}
