package com.juancastelli.uala.controller;

import com.juancastelli.uala.model.Tweet;
import com.juancastelli.uala.model.User;
import com.juancastelli.uala.service.TweetService;
import com.juancastelli.uala.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TweetsControllerTest {

    @Autowired
    TweetService tweetService;
    @Autowired
    TweetsController tweetsController;
    @Autowired
    UserService userService;


    @Test
    @Transactional
    public void test_create_tweet_with_message_and_user_id() throws Exception {


        // Arrange
        Integer userID = userService.save(new User("juan")).getId();
        String message = "Hello world";

        // Act
        tweetsController.createTweet(userID, message);

        // Assert
        // Verify that the tweet is created with the given message and user ID
        Optional<Tweet> tweet = tweetService.get(1);
        assertTrue(tweet.isPresent());
        assertEquals(message, tweet.get().getMessage());
        assertEquals(userID, tweet.get().getOwner().getId());
    }
}