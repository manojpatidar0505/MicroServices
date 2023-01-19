package com.learn.micro.user.services.impl;

import com.learn.micro.user.entity.User;
import com.learn.micro.user.exception.ResourceNotFoundException;
import com.learn.micro.user.repository.UserRepository;
import com.learn.micro.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return repository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return repository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return repository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Record Not found against requested id "+userId));
    }
}
