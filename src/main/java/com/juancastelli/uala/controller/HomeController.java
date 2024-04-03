package com.juancastelli.uala.controller;

import com.juancastelli.uala.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {
    @Autowired
    private RedisTemplate<Integer, Tweet> redis;
    //mover esto a un service

    @GetMapping("/home")
    public List<Tweet> addTweet(@RequestHeader Integer userID ,
                                @RequestParam(defaultValue = "0") Integer start ,
                                @RequestParam(defaultValue = "10") Integer offset ) {
        return redis.opsForList().range(userID, start ,start+offset);
    }
}
