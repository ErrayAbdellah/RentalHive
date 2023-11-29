package com.rentalHive.rentalHive.repository;

import com.rentalHive.rentalHive.model.entities.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDemandeRepo extends JpaRepository<Demande, Long> {

}
