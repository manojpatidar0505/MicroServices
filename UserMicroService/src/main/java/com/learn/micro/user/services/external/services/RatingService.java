package com.learn.micro.user.services.external.services;

import com.learn.micro.user.entity.Hotel;
import com.learn.micro.user.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {


    @PostMapping("/ratings")
    Rating createRating(Rating rating);

    

}
