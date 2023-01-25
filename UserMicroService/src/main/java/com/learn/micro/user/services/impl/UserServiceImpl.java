package com.learn.micro.user.services.impl;

import com.learn.micro.user.entity.Hotel;
import com.learn.micro.user.entity.Rating;
import com.learn.micro.user.entity.User;
import com.learn.micro.user.exception.ResourceNotFoundException;
import com.learn.micro.user.repository.UserRepository;
import com.learn.micro.user.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        Rating[] list = restTemplate.getForObject(url, Rating[].class);
        List<Rating> ratingLis = Arrays.stream(list).collect(Collectors.toList());
        logger.info("Ratings:{}", list.toString());
        List<Rating> ratingList = ratingLis.stream().map(rating -> {
            String hotelUrl = "http://localhost:8082/hotels/";
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity(hotelUrl + rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }
}
