package com.juancastelli.uala.service;

import com.juancastelli.uala.model.Tweet;
import com.juancastelli.uala.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetService {

    @Autowired
    private TweetRepository tweetRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private HomeService homeService;

    public void createTweet(String message, Integer userId) {
        userService.get(userId).ifPresent(user -> {
            Tweet tweet = tweetRepository.save(new Tweet(user, message));
            user.getTweets().add(tweet);
            userService.save(user);

            homeService.updateFollowersHome(user,tweet);
        });
    }

    public Optional<Tweet> get(Integer id) {
        return tweetRepository.findById(id);
    }
}