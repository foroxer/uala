package com.juancastelli.uala.service;

import com.juancastelli.uala.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    @Transactional
    public void test_follow_user_successfully() {
        // Arrange
        User user1 = new User("test1");
        User user2 = new User("test2");
        userService.save(user1);
        userService.save(user2);

        // Act
        assertDoesNotThrow(() -> userService.follow(user1.getId(), user2.getId()));

        // Assert
        assertTrue(userService.get(user2.getId()).get().getFollowers().contains(user1));
    }

    @Test
    public void test_follow_user_with_invalid_user_id() {
        // Arrange
        Integer userId = 100;
        Integer followId = 200;

        // Act and Assert
        assertThrows(Exception.class, () -> userService.follow(userId, followId));
    }
}