package com.cinepantin.shop.service;

import com.cinepantin.shop.domain.Basket;
import com.cinepantin.shop.domain.User;
import com.cinepantin.shop.domain.UserBasket;
import com.cinepantin.shop.domain.Visitor;

public interface BasketService {

	public Basket find(Visitor visitor);

	public UserBasket find(int id);
	
	public UserBasket find(User user);
	
	public void save (UserBasket userBasket);
	
	public void remove(int id);

}
