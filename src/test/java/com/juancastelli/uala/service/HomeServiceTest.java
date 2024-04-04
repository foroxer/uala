package com.juancastelli.uala.service;

import com.juancastelli.uala.model.Tweet;
import com.juancastelli.uala.model.User;
import com.juancastelli.uala.repository.HomeRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class HomeServiceTest {
    @Mock
    HomeRepository homeRepository;

    @InjectMocks
    @Resource
    HomeService homeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_update_user_home() {
        // Arrange
        User user = new User(1, "John", new ArrayList<>(), new HashSet<>());
        Tweet tweet = new Tweet(user, "Hello world");

        // Act
        homeService.updateUserHome(user, tweet);

        // Assert
        verify(homeRepository, times(1)).updateHome(user, tweet);
    }

    @Test
    public void test_update_multiple_users_home() {
        // Arrange
        User user = new User("test1");
        User follower1 = new User("test2");
        User follower2 = new User("test2");
        User follower3 = new User("test3");
        user.getFollowers().addAll(new ArrayList<>() {{
            add(follower2);
            add(follower1);
            add(follower3);
        }});
        Tweet tweet = new Tweet(user, "Hello world");

        // Act
        homeService.updateFollowersHome(user, tweet);

        // Assert
        verify(homeRepository, times(1)).updateHome(follower2, tweet);
        verify(homeRepository, times(1)).updateHome(follower1, tweet);
        verify(homeRepository, times(1)).updateHome(follower3, tweet);
    }

    @Test
    public void test_no_followers() {
        // Arrange
        User user = new User("test1");
        Tweet tweet = new Tweet(user, "Hello world");

        // Act
        homeService.updateFollowersHome(user, tweet);

        // Assert
        verify(homeRepository, never()).updateHome(any(User.class), any(Tweet.class));
    }
}
