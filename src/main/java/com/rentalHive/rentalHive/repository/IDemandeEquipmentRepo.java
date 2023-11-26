package com.rentalHive.rentalHive.repository;

import com.rentalHive.rentalHive.model.entities.DemandeEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDemandeEquipmentRepo extends JpaRepository <DemandeEquipment, Long> {
}
