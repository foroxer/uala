package com.juancastelli.uala.controller;

import com.juancastelli.uala.model.Tweet;
import com.juancastelli.uala.service.HomeService;
import com.juancastelli.uala.service.TweetService;
import com.juancastelli.uala.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        userService.get(userID).ifPresent(user -> {
            Tweet tweet = tweetService.save(new Tweet(user, message));
            user.getTweets().add(tweet);
            userService.save(user);

            user.getFollowers().forEach(follower -> {
				homeService.updateUserHome(user,tweet);
            });
        });
    }
}
