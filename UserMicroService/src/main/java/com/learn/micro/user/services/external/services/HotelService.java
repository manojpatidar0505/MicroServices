package com.learn.micro.user.services.external.services;

import com.learn.micro.user.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {


    @GetMapping("hotels/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);

}
