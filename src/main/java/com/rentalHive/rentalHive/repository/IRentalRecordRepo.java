package com.rentalHive.rentalHive.repository;

import com.rentalHive.rentalHive.model.entities.RentalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IRentalRecordRepo extends JpaRepository<RentalRecord, Long> {
    List<RentalRecord> findByEquipmentEquipmentId(Long equipmentId);
}
