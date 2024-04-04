package com.juancastelli.uala.service;

import com.juancastelli.uala.model.Tweet;
import com.juancastelli.uala.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    @Autowired
    private RedisTemplate<Integer, Tweet> homeRepository;

    public void updateUserHome(User user, Tweet tweet) {
        homeRepository.opsForList().leftPush(user.getId(), tweet);
    }

    public List<Tweet> getHome(Integer userId, Integer start, Integer offset) {
        return homeRepository.opsForList().range(userId, start, start + offset);
    }
}
