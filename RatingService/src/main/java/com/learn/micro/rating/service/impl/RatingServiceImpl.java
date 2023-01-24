package com.learn.micro.rating.service.impl;

import com.learn.micro.rating.entity.Ratings;
import com.learn.micro.rating.exception.ResourceNotFoundException;
import com.learn.micro.rating.repository.RatingRepository;
import com.learn.micro.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository repository;

    @Override
    public Ratings createRating(Ratings ratings) {
        String id = UUID.randomUUID().toString();
        ratings.setRatingId(id);
        return repository.save(ratings);
    }

    @Override
    public List<Ratings> getAllRatings() {

        return repository.findAll();
    }

    @Override
    public List<Ratings> getRatingByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<Ratings> getRatingByHotelId(String hotelId) {
        return repository.findByHotelId(hotelId);
    }
}
