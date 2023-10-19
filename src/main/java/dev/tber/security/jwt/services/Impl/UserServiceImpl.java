package dev.tber.security.jwt.services.Impl;

import dev.tber.security.jwt.models.entities.User;
import dev.tber.security.jwt.repositories.UserRepository;
import dev.tber.security.jwt.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
