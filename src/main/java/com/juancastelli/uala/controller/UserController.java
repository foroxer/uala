package com.juancastelli.uala.controller;

import com.juancastelli.uala.model.User;
import com.juancastelli.uala.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("user/follow/{followId}")
    public void follow(@RequestHeader Integer userID, @PathVariable Integer followId) throws Exception {
        userService.follow(userID, followId);
    }

    @GetMapping("/user/{id}")
    public Optional<User> get(@PathVariable Integer id) {
        return userService.get(id);
    }

    @PostMapping("/user")
    public User create(@RequestParam String name) {
        return userService.save(new User(name));
    }
}
