package com.rentalHive.rentalHive.repository;

import com.rentalHive.rentalHive.model.entities.RentalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IRentalRecordRepo extends JpaRepository<RentalRecord, Long> {
    Optional<RentalRecord> findByEquipmentEquipmentId(Long equipmentId);
    @Query("SELECT r FROM RentalRecord r WHERE r.equipment.equipmentId = :equipmentId")
    List<RentalRecord> findByEquipmentId(@Param("equipmentId") Long equipmentId);
}
