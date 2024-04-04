package com.juancastelli.uala.service;

import com.juancastelli.uala.model.Tweet;
import com.juancastelli.uala.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    public Tweet save(Tweet t) {
        return tweetRepository.save(t);
    }

    public Optional<Tweet> get(Tweet t) {
        return tweetRepository.findById(t.getId());
    }
}