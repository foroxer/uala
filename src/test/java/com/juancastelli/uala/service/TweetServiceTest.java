package com.juancastelli.uala.service;

import com.juancastelli.uala.model.Tweet;
import com.juancastelli.uala.model.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TweetServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    TweetService tweetService;

    @Test
    @Transactional
    public void test_create_tweet_with_valid_message_and_user_id() throws Exception {
        // Arrange
        User user = new User("test");
        userService.save(user);

        String message = "Hello world";
        Integer userId = user.getId();

        // Act
        Tweet result = tweetService.createTweet(message, userId);

        // Assert
        assertNotNull(result);
        assertEquals(message, result.getMessage());
        assertEquals(user, result.getOwner());
    }
}