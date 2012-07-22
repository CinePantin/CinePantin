package com.cinepantin.shop.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * A Customer (Domain Entity) is a {@link User} with a default address (TODO: make this a Domain Entity)
 *
 */
public class Customer extends User {

	private String TODO;
	
	private List<Address> addressList;
	public List<Address> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}



	private DefaultAddress defaultAddress;
	public DefaultAddress getDefaultAddress() {
		return defaultAddress;
	}
	public void setDefaultAddress(DefaultAddress defaultAddress) {
		this.defaultAddress = defaultAddress;
	}



	private List<Order> orders;
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
	
	/** JPA constructor **/
	protected Customer() {
		this.defaultAddress = new DefaultAddress(); // TODO: implement
		this.addressList = new ArrayList<Address>();
		this.orders = new ArrayList<Order>();
	}
	
	
	
	/**
	 * Creates an {@link Customer} promoted from a {@link User} 
	 * 
	 * @param user to be promoted to a Customer
	 */
	public Customer(User user, DefaultAddress defaultAddress) {
		this();
		this.setLogin(user.getLogin());
		this.setPassword(user.getPassword());
		this.setUserBasket(user.getUserBasket());
		this.defaultAddress = new DefaultAddress();
		// TODO implement as : this.defaultAddress = defaultAddress;
		
	}
	
	

}
