package com.learn.micro.rating.service;

import com.learn.micro.rating.entity.Ratings;

import java.util.List;

public interface RatingService {

    Ratings createRating(Ratings ratings);

    List<Ratings> getAllRatings();

    List<Ratings> getRatingByUserId(String userId);

    List<Ratings> getRatingByHotelId(String hotelId);

}
