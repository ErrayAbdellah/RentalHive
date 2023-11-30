package com.rentalHive.rentalHive.repository;

import com.rentalHive.rentalHive.model.entities.Devis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDevisRepo extends JpaRepository<Devis, Long> {
    @Override
    List<Devis> findAll();
}
