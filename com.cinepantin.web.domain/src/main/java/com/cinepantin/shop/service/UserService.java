package com.cinepantin.shop.service;

import com.cinepantin.shop.domain.User;

public interface UserService {

	public User find(int id);
	
	public void save (User user);
	
	public Integer getUserIdFromCredentials(String login, String password);

}
