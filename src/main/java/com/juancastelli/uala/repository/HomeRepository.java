package com.juancastelli.uala.repository;

import com.juancastelli.uala.model.Tweet;
import com.juancastelli.uala.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HomeRepository {
    @Autowired
    RedisTemplate<Integer, Tweet> redisHomeImpl;

    public void updateHome(User user, Tweet tweet) {
        redisHomeImpl.opsForList().leftPush(user.getId(), tweet);
    }

    public List<Tweet> getHome(Integer userId, Integer start, Integer offset) {
        return redisHomeImpl.opsForList().range(userId, start, start + offset);
    }


}


