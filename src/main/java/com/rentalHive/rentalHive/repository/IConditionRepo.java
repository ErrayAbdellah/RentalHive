package com.rentalHive.rentalHive.repository;

import com.rentalHive.rentalHive.model.entities.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IConditionRepo extends JpaRepository<Condition, Long> {
    void deleteById(Long conditionId);

     Condition save(Condition existingCondition);

    Optional<Condition> findById(Long conditionId);
    List<Condition> findConditionByContrat_Id(Long contract_Id);

    @Query("SELECT c FROM Condition c")
    List<Condition> getAllConditions();
}
