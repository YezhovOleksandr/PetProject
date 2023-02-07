package com.example.petprojectbackend.services;

import com.example.petprojectbackend.model.User;
import java.util.*;
public interface UserService {
    User saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(User user);

    void deleteUser(Long id);

}
