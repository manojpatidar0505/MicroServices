package com.learn.micro.rating.controllers;

import com.learn.micro.rating.entity.Ratings;
import com.learn.micro.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Ratings> createRatings(@RequestBody Ratings ratings) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(ratings));
    }

    @GetMapping("hotels/{hotelId}")
    public ResponseEntity<List<Ratings>> getRatingByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByHotelId(hotelId));
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<List<Ratings>> getRatingByUserId(@PathVariable String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<Ratings>> getAllRatings() {
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getAllRatings());
    }


}
