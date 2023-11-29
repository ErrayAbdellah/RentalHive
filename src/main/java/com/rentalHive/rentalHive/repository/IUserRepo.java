package com.rentalHive.rentalHive.repository;

import com.rentalHive.rentalHive.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepo extends JpaRepository<User, Long> {
    Optional<User> findById(long userId);
}
