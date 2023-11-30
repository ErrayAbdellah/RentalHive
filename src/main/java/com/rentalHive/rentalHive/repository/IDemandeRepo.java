package com.rentalHive.rentalHive.repository;

import com.rentalHive.rentalHive.enums.Category;
import com.rentalHive.rentalHive.enums.State;
import com.rentalHive.rentalHive.model.dto.DemandeDTO;
import com.rentalHive.rentalHive.model.dto.EquipmentDTO;
import com.rentalHive.rentalHive.model.entities.Demande;
import com.rentalHive.rentalHive.model.entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IDemandeRepo extends JpaRepository<Demande, Long> {
    List<Demande> findDemandeByState(State state);

    @Query("SELECT d FROM Demande d " +
            "JOIN d.equipment e " +
            "WHERE e.equipmentId IN :equipmentIds " +
            "AND ((:demandeDate BETWEEN d.demande_date AND d.date_retour) OR " +
            "(:dateRetour BETWEEN d.demande_date AND d.date_retour))")
    List<Demande> findExistingReservations(
            @Param("equipmentIds") List<Long> equipmentIds,
            @Param("demandeDate") Date demandeDate,
            @Param("dateRetour") Date dateRetour
    );

}
