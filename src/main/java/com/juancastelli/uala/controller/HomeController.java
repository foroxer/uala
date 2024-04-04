package com.juancastelli.uala.controller;

import com.juancastelli.uala.model.Tweet;
import com.juancastelli.uala.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    HomeService homeService;

    @GetMapping("/home")
    public List<Tweet> addTweet(@RequestHeader Integer userID, @RequestParam(defaultValue = "0") Integer start, @RequestParam(defaultValue = "10") Integer offset) {
        return homeService.getHome(userID, start, offset);
    }
}
