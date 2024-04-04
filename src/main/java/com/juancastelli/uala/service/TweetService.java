package com.juancastelli.uala.service;

import com.juancastelli.uala.model.Tweet;
import com.juancastelli.uala.model.User;
import com.juancastelli.uala.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TweetService {

    private final TweetRepository tweetRepository;
    private final UserService userService;
    private final HomeService homeService;

    @Autowired
    public TweetService(TweetRepository tweetRepository, UserService userService, HomeService homeService) {
        this.tweetRepository = tweetRepository;
        this.userService = userService;
        this.homeService = homeService;
    }


    public Tweet createTweet(String message, Integer userId) throws Exception {
        Optional<User> optionalUser = userService.get(userId);
        optionalUser.orElseThrow(() -> new Exception("user not found"));

        User user = optionalUser.get();
        Tweet tweet = tweetRepository.save(new Tweet(user, message));
        user.getTweets().add(tweet);
        userService.save(user);
        homeService.updateFollowersHome(user, tweet);

        return tweet;
    }

    public Optional<Tweet> get(Integer id) {
        return tweetRepository.findById(id);
    }
}