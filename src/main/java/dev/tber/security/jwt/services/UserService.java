package dev.tber.security.jwt.services;

import dev.tber.security.jwt.models.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
}
