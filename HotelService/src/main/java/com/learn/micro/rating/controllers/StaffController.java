package com.learn.micro.rating.controllers;

import com.learn.micro.rating.entity.Hotel;
import com.learn.micro.rating.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("staffs")
public class StaffController {


    @GetMapping()
    public ResponseEntity<List<String>> getStaff() {
        return ResponseEntity.status(HttpStatus.OK).body(Arrays.asList("MANOJ","RAHUL","KAMAL"));
    }


}
