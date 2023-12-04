package com.rentalHive.rentalHive.service;

import com.rentalHive.rentalHive.model.entities.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IUserService {
    Optional<User> getUserById(Long recordId);
}
