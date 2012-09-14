package com.cinepantin.shop.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * A Customer (Domain Entity) is a {@link User} with a default address (TODO: make this a Domain Entity)
 *
 */
// @Entity
// @DiscriminatorValue(value="Customer")
public class Customer 
//			extends User 
{

	private String TODO;


//	@OneToOne(targetEntity=DefaultAddress.class)
//	private DefaultAddress defaultAddress;
//	public DefaultAddress getDefaultAddress() {
//		return defaultAddress;
//	}
//	public void setDefaultAddress(DefaultAddress defaultAddress) {
//		this.defaultAddress = defaultAddress;
//	}
	
	
// TODO: uncomment
//	private List<Order> orders;
//	public List<Order> getOrders() {
//		return orders;
//	}
//	public void setOrders(List<Order> orders) {
//		this.orders = orders;
//	}
	
	
	
	/** JPA empty constructor **/
	public Customer() {
		// super();
		// this.defaultAddress = new DefaultAddress(); // TODO: implement
		// this.addressList = new ArrayList<Address>();
		// this.addressList = new HashMap<String, Address>();
		//this.orders = new ArrayList<Order>();
	}
	
	
	
	/**
	 * Creates an {@link Customer} promoted from a {@link User} 
	 * 
	 * @param user to be promoted to a Customer
	 */
	public Customer(User user, DefaultAddress defaultAddress) {
		this();
//		this.setLogin(user.getLogin());
//		this.setPassword(user.getPassword());
		// this.setUserBasket(user.getUserBasket());
		// this.defaultAddress = new DefaultAddress();
		// TODO implement as : this.defaultAddress = defaultAddress;		
	}
	
	

}
