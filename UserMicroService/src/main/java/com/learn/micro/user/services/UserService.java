package com.learn.micro.user.services;


import com.learn.micro.user.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUser();

    User getUser(String userId);
}
