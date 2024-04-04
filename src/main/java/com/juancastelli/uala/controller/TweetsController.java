package com.juancastelli.uala.controller;

import com.juancastelli.uala.model.Tweet;
import com.juancastelli.uala.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TweetsController {

    private final TweetService tweetService;

    @Autowired

    public TweetsController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @PostMapping("/tweet")
    public Tweet createTweet(@RequestHeader Integer userID, @RequestParam String message) throws Exception {
        return tweetService.createTweet(message, userID);
    }

    @GetMapping("/tweet/{id}")
    public Optional<Tweet> get(@PathVariable Integer id) {
        return tweetService.get(id);
    }
}
