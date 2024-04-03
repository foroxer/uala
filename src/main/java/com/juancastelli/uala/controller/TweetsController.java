package com.juancastelli.uala.controller;

import com.juancastelli.uala.model.Tweet;
import com.juancastelli.uala.service.TweetService;
import com.juancastelli.uala.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TweetsController {

	@Autowired
	private UserService userService;
	@Autowired
	private TweetService tweetService;

	@Autowired
	private RedisTemplate<Integer, Tweet> redis;
	//mover esto a un service




	@PostMapping("/tweet")
	public void createTweet( @RequestHeader Integer userID, @RequestParam String message) {
		userService.get(userID).ifPresent(user -> {
			Tweet tweet = tweetService.save(new Tweet(user, message));
			user.getTweets().add(tweet);
			userService.save(user);

			user.getFollowers().forEach(follower -> {
				redis.opsForList().leftPush(follower.getId(), tweet);
			});
		});
	}
}
