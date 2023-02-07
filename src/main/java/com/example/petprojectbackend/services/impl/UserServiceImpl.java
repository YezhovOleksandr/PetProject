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

    //TODO Write exception and create user DTO for changing everything except password and vica versa
    @Override
    public User updateUser(User user, Long id) {
        return userDao.findById(id)
                .map(user1 -> {
                    user1.setFirst_name(user.getFirst_name());
                    user1.setLast_name(user.getLast_name());
                    user1.setUsername(user.getUsername());
                    user1.setEmail(user.getEmail());
                    user1.setPassword(user.getPassword());
                    return userDao.save(user1);
                }).orElseThrow(()-> {throw new RuntimeException();});
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

