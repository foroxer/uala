package com.juancastelli.uala.controller;

import com.juancastelli.uala.model.Tweet;
import com.juancastelli.uala.model.User;
import com.juancastelli.uala.service.HomeService;
import com.juancastelli.uala.service.TweetService;
import com.juancastelli.uala.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TweetsController {

    @Autowired
    private UserService userService;
    @Autowired
    private TweetService tweetService;
    @Autowired
    private HomeService homeService;

    @PostMapping("/tweet")
    public void createTweet(@RequestHeader Integer userID, @RequestParam String message) {
        tweetService.createTweet(message,userID);
    }
    @GetMapping("/tweet/{id}")
    public Optional<Tweet> get(@PathVariable Integer id){
        return tweetService.get(id);
    }
}
