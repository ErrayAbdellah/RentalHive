package com.rentalHive.rentalHive.repository;

import com.rentalHive.rentalHive.model.entities.Devis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDevisRepo extends JpaRepository<Devis, Long> {
    List<Devis> findAll();
    Optional<Devis> findByDemande_Id(long demande_id);
}
