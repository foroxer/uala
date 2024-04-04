package com.juancastelli.uala.controller;

import com.juancastelli.uala.model.User;
import com.juancastelli.uala.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserControllerTest {
    @Autowired
    UserService userService;
    @Autowired
    UserController userController;

    @Test
    @Transactional

    public void test_returns_empty_optional_user_object_when_given_invalid_user_id() {
        // Arrange
        Integer id = 100;

        // Act
        Optional<User> result = userController.get(id);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    @Transactional
    public void test_follows_user_with_valid_ids() throws Exception {
        // Arrange
        userService.save(new User("test1"));
        userService.save(new User("test2"));

        Integer userID = 1;
        Integer followId = 2;

        // Act
        userController.follow(userID, followId);

        // Assert
        Optional<User> user = userService.get(userID);
        Optional<User> followedUser = userService.get(followId);
        assertTrue(user.isPresent());
        assertTrue(followedUser.isPresent());
        assertTrue(followedUser.get().getFollowers().contains(user.get()));
    }

    @Test
    public void test_throws_exception_with_null_user_id() {
        // Arrange

        Integer userID = null;
        Integer followId = 2;

        // Act & Assert
        assertThrows(Exception.class, () -> userController.follow(userID, followId));
    }

    @Test
    public void test_throws_exception_with_null_follow_id() {
        // Arrange

        Integer userID = 1;
        Integer followId = null;

        // Act & Assert
        assertThrows(Exception.class, () -> userController.follow(userID, followId));
    }
}