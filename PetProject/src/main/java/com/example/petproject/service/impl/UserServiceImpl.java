package com.example.petproject.service.impl;

import com.example.petproject.dao.UserDao;
import com.example.petproject.models.User;
import com.example.petproject.service.UserService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }


    //TODO create exception for USER NOT FOUND
    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userDao.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public User updateUser(User user) {

        return userDao.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> byId = getUserById(id);
        userDao.delete(byId.orElseThrow(RuntimeException::new));
    }
}
