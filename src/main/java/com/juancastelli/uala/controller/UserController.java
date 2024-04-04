package com.juancastelli.uala.controller;

import com.juancastelli.uala.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/follow/{followId}")
    public void createTweet(@RequestHeader Integer userID, @PathVariable Integer followId) throws Exception {
        userService.follow(userID, followId);
    }
}
