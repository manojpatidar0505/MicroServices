package com.learn.micro.user.services.impl;

import com.learn.micro.user.entity.Rating;
import com.learn.micro.user.entity.User;
import com.learn.micro.user.exception.ResourceNotFoundException;
import com.learn.micro.user.repository.UserRepository;
import com.learn.micro.user.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
        User user = repository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Record Not found against requested id " + userId));
        String url = "http://localhost:8083/ratings/users/" + userId;
        ArrayList<Rating> list = restTemplate.getForObject(url, ArrayList.class);
        logger.info("Ratings:{}", list.toString());
        user.setRatings(list);
        return user;
    }
}
