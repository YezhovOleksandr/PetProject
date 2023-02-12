package com.example.petproject.service;

import com.example.petproject.models.User;
import java.util.*;
public interface UserService {
    User saveUser(User user);

    List<User> getAll();

    Optional<User> getUserById(Long id);

    User updateUser(User user);

    void deleteUser(Long id);
}
