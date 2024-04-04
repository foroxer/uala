package com.juancastelli.uala.service;

import com.juancastelli.uala.model.Tweet;
import com.juancastelli.uala.model.User;
import com.juancastelli.uala.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    @Autowired
    private HomeRepository homeRepository;

    public void updateFollowersHome(User user, Tweet tweet){
        user.getFollowers().forEach(follower -> {
            updateUserHome(follower,tweet);
        });
    }
    public void updateUserHome(User user, Tweet tweet) {
        homeRepository.updateHome( user,  tweet);
    }

    public List<Tweet> getHome(Integer userId, Integer start, Integer offset) {
        return homeRepository.getHome( userId,  start,  offset);
    }
}
