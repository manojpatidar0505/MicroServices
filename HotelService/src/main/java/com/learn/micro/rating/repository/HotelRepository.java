package com.learn.micro.rating.repository;

import com.learn.micro.rating.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, String> {
}
