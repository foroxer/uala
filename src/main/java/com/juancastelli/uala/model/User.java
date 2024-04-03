package com.juancastelli.uala.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User implements Serializable  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String name;

	@OneToMany(fetch = FetchType.LAZY)
	private List<Tweet> tweets = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<User> followers = new HashSet<>();


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Tweet> getTweets() {
		return tweets;
	}
	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	public Set<User> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<User> followers) {
		this.followers = followers;
	}

	public User() {
	}

	public User(String name) {
		this.name = name;
	}

	public User(Integer id, String name, List<Tweet> tweets, Set<User> followers) {
		this.id = id;
		this.name = name;
		this.tweets = tweets;
		this.followers = followers;
	}
}
