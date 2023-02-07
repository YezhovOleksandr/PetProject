package com.example.petprojectbackend.services.impl;

import com.example.petprojectbackend.dao.UserDao;
import com.example.petprojectbackend.model.User;
import com.example.petprojectbackend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    //TODO create personal exception for this method
    @Override
    public User getUserById(Long id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    public User updateUser(User user) {
        return userDao.save(user);
    }

    //TODO create exception for this method
    @Override
    public void deleteUser(Long id) {
        Optional<User> byId = userDao.findById(id);
        userDao.delete(byId.orElseThrow(() ->{
            throw new RuntimeException();
        }));
        }

    }

