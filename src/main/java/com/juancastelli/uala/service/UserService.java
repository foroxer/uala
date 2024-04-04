package com.juancastelli.uala.service;

import com.juancastelli.uala.model.User;
import com.juancastelli.uala.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> get(Integer id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }


    public void follow(Integer userId, Integer followId) throws Exception {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<User> optionalToFollowUser = userRepository.findById(followId);

        optionalUser.orElseThrow(() -> new Exception("user not found"));
        optionalToFollowUser.orElseThrow(() -> new Exception("user to follow not found"));

        optionalToFollowUser.ifPresent(
                user -> {
                    user.getFollowers().add(optionalUser.get());
                    save(user);
                });
    }
}
