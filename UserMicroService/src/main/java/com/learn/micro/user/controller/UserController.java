package com.learn.micro.user.controller;

import com.learn.micro.user.entity.User;
import com.learn.micro.user.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("hello")
    public String test() {
        return "hello";
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        User user = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    //ratingHotelFallback method for circuit breaker
    ResponseEntity<User> ratingHotelFallback(String userId, Exception e) {
        logger.info("Fallback is executed because service is down:{}" + e.getMessage());
        User user = User.builder().userId("ss").email("dummy@dummy.com").name("dummy").about("This is dummy user").build();
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<User>> allUsers() {
        List<User> users = userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
}
