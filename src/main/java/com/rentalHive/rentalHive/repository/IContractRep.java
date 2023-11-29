package com.rentalHive.rentalHive.repository;

import com.rentalHive.rentalHive.model.entities.Contrat;
import com.rentalHive.rentalHive.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IContractRep extends JpaRepository<Contrat,Long> {
    List<Contrat> findByStatus(Status status);
    @Query(value = "SELECT * FROM contrat c WHERE c.user_id = :userId AND c.status = :status", nativeQuery = true)
    List<Contrat> findByUserIdAndStatus(@Param("userId") Long userId, @Param("status") Status status);
}
