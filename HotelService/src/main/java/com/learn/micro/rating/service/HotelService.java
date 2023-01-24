package com.learn.micro.rating.service;

import com.learn.micro.rating.entity.Hotel;

import java.util.List;

public interface HotelService {

    Hotel createHotel(Hotel hotel);

    List<Hotel> getAllHotels();

    Hotel getHotelById(String id);

}
