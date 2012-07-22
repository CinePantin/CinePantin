package com.cinepantin.shop.domain;


/**
 * A User is someone with a login. (TODO: create it as a Domain Entity)<br /> 
 * Could be named <em>RegisteredUser</em>, as opposed to an anonymous {@link Visitor}.
 *
 */
public class User extends Visitor {

	private String TODO;
	
	
	
	/** DON'T build code on this for now: the implementation could be changed with the use of Spring Security **/
	private String login;
	protected String getLogin() { // Sensible... take care with visibility
		return this.login;
	}
	void setLogin(String login) { // Sensible... take care with visibility
		this.login = login;
	}



	/** DON'T build code on this for now: the implementation could be changed with the use of Spring Security **/
	private String password;
	String getPassword() { // Sensible... take care with visibility
		return this.password;
	}
	void setPassword(String password) { // Sensible... take care with visibility
		this.password = password;
	}



	private UserBasket userBasket;
	/** returns the <em>persistent</em> {@link UserBasket} -- as opposed to {@link Visitor#getBasket()} 
	 * ({@link Basket}s do NOT persist...) **/
	public UserBasket getUserBasket() {
		return this.userBasket;
	}
	protected void setUserBasket(UserBasket userBasket) {
		this.userBasket = userBasket;
	}

	
	/** JPA constructor **/
	protected User() {
		this.setUserBasket(new UserBasket());
	}
	

	
	public User(Visitor visitor, String login, String password) {
		this.setLogin(login);
		this.setPassword(password);
		this.setUserBasket(new UserBasket(visitor.getBasket()));
	}
}
