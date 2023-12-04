package com.rentalHive.rentalHive.repository;

import com.rentalHive.rentalHive.model.entities.Archive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArchieveRepo extends JpaRepository<Archive, Long> {
    
}
