package com.rentalHive.rentalHive.repository;

import com.rentalHive.rentalHive.model.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImageRepo extends JpaRepository<Image,Long> {
}
