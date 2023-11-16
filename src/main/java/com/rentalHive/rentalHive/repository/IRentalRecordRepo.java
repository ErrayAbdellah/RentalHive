package com.rentalHive.rentalHive.repository;

import com.rentalHive.rentalHive.model.entities.RentalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRentalRecordRepo extends JpaRepository<RentalRecord,Long> {
}
