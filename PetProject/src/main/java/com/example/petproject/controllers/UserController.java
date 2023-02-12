package com.example.petproject.controllers;

import com.example.petproject.models.User;
import com.example.petproject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/users")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUserByid(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    //TODO create exception for user not found
    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User newUser) {
       return userService.getUserById(id)
                .map(user -> {
                    user.setFirst_name(newUser.getFirst_name());
                    user.setLast_name(newUser.getLast_name());
                    user.setUsername(newUser.getUsername());
                    user.setPassword(newUser.getPassword());
                    user.setEmail(newUser.getEmail());
                    return userService.saveUser(user);
                }).orElseThrow(RuntimeException::new);
    }
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

    }

}
