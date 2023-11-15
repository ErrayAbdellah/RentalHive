package com.rentalHive.rentalHive.repository;

import com.rentalHive.rentalHive.model.entities.Equipement;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipementRepo extends JpaRepository<Equipement,Integer>
{


}
