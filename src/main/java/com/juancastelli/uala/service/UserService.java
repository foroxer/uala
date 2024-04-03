package com.juancastelli.uala.service;

import com.juancastelli.uala.model.User;
import com.juancastelli.uala.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	private final UserRepository userRepository;
	public List<User> findAll(){
		return userRepository.findAll();
	}

	public Optional<User> get(Integer id){
		return userRepository.findById(id);
	}

	public User save(User user){
		return userRepository.save(user);
	}

	public void follow(User user, User follow) throws Exception {
		follow(user.getId(),follow.getId());
	}
	public void follow(Integer userId, Integer followId) throws Exception {
		Optional<User> optFollowUser = userRepository.findById(followId);

		if(optFollowUser.isEmpty()) throw new Exception("user to follow not found");

		userRepository.findById(userId).ifPresent(
			 user -> {
				 user.getFollowers().add(optFollowUser.get());
				 save(user);
		 });
	}
}
