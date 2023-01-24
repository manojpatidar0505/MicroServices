package com.learn.micro.rating.service.impl;

import com.learn.micro.rating.entity.Hotel;
import com.learn.micro.rating.exception.ResourceNotFoundException;
import com.learn.micro.rating.repository.HotelRepository;
import com.learn.micro.rating.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository repository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String id = UUID.randomUUID().toString();
        hotel.setId(id);
        return repository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return repository.findAll();
    }

    @Override
    public Hotel getHotelById(String id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
    }
}
