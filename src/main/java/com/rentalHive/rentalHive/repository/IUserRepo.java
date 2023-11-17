package com.rentalHive.rentalHive.repository;

import com.rentalHive.rentalHive.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User,Long> {
}
