package com.rentalHive.rentalHive.repository;

import com.rentalHive.rentalHive.model.entities.Contrat;
import com.rentalHive.rentalHive.enums.Status;
import com.rentalHive.rentalHive.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IContractRep extends JpaRepository<Contrat,Long> {
    List<Contrat> findByStatus(Status status);
//    @Query(value = "SELECT * FROM contrat c WHERE c.user_id = :userId AND c.status = :status", nativeQuery = true)
//    List<Contrat> findByUserIdAndStatus(@Param("userId") Long userId, @Param("status") Status status);
//    List<Contrat> findAllByUser(long userId,Status status);
    List<Contrat> findAllByUserAndStatus(Optional<User> user, Status status);
}
