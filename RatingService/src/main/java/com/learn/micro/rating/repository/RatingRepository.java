package com.learn.micro.rating.repository;

import com.learn.micro.rating.entity.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Ratings, String> {
List<Ratings> findByUserId(String userId);
    List<Ratings> findByHotelId(String hotelId);
}
