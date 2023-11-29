package com.rentalHive.rentalHive.service.implementations;

import com.rentalHive.rentalHive.model.entities.User;
import com.rentalHive.rentalHive.repository.IUserRepo;
import com.rentalHive.rentalHive.service.IUserService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserServiceImpl implements IUserService {

    private final IUserRepo userRepo;

    public UserServiceImpl(IUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepo.findById(userId);
    }
}
