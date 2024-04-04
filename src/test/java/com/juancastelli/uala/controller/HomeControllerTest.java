package com.juancastelli.uala.controller;

import com.juancastelli.uala.model.Tweet;
import com.juancastelli.uala.model.User;
import com.juancastelli.uala.service.TweetService;
import com.juancastelli.uala.service.UserService;
import com.juancastelli.uala.testUtil.EmbededRedis;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class HomeControllerTest {

    @Autowired
    HomeController homeController;
    @Autowired
    UserService userService;
    @Autowired
    TweetService tweetService;
    @Autowired
    EmbededRedis embededRedis;

    @BeforeEach
    void setUp() throws IOException {
        embededRedis.startRedis();
    }

    @AfterEach
    void tearDown() throws IOException {
        embededRedis.stopRedis();
    }

    @Test
    public void test_valid_user_id() {

        // Arrange
        Integer userID = 1;
        Integer start = 0;
        Integer offset = 10;

        // Act
        List<Tweet> result = homeController.getHome(userID, start, offset);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    @Transactional
    public void test_valid_user_id_when_have_tweets() throws Exception {

        // Arrange
        User user1 = new User("test1");
        User user2 = new User("test2");
        userService.save(user1);
        userService.save(user2);

        userService.follow(user1.getId(), user2.getId());
        String tweetMessage = "holamundo";
        tweetService.createTweet(tweetMessage, user2.getId());

        Integer start = 0;
        Integer offset = 10;

        // Act
        List<Tweet> result = homeController.getHome(user1.getId(), start, offset);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(result.get(0).getMessage(), tweetMessage);
    }
}

