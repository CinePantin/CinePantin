package com.cinepantin.shop.domain;

import java.util.List;
import java.util.Map;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;


/**
 * A User (Domain Entity) is a {@link Visitor} with a login.<br /> 
 * Could have been named <em>RegisteredUser</em>, as opposed to an anonymous {@link Visitor}.
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING, length=8)
@DiscriminatorValue(value="User")
public class User 
				extends Visitor 
{
	
	
	
	private Integer userId;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	
	/** DON'T build code on this for now: the implementation could be changed with the use of Spring Security **/
	private String login;
	public String getLogin() { // Sensible... take care with visibility
		return this.login;
	}
	public void setLogin(String login) { // Sensible... take care with visibility
		this.login = login;
	}



	/** DON'T build code on this for now: the implementation could be changed with the use of Spring Security **/
	public String password;
	String getPassword() { // Sensible... take care with visibility
		return this.password;
	}
	public void setPassword(String password) { // Sensible... take care with visibility
		this.password = password;
	}

	
	/**
	 * hides (sub-types) Basket super.basket
	 */
	protected UserBasket basket; // Override type defined in parent class
	/**
	 * Return this user's {@link UserBasket} (persistent entity).
	 */
	@Override
	@OneToOne(
			targetEntity=UserBasket.class
			, mappedBy="user"
			, optional=true // relationship is bi-directional, so we will need a user without basket before the UserBasket can reference User (!) (they don't speak about that in books...)
			// , fetch=FetchType.LAZY
			// , cascade={CascadeType.PERSIST} // TODO: decide whether to cascade User persistence to UserBasket persistence
		) 
	public UserBasket getBasket() { // Hides Basket super.getBasket (defines mapping with UserBasket entity, when Basket is no entity)
		if (this.basket == null) {
			this.basket = new UserBasket(this);
		}
		return this.basket;
	}
	public void setBasket(UserBasket userBasket) {
		this.basket = userBasket;
	}


	
	
	private List<Order> orders;
	@OneToMany(
			mappedBy="user"
	)
	public List<Order> getOrders() {
		return this.orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	

	
	
	private Map<String, RolodexAddress> rolodex;
	/**
	 * A user's Rolodex is a Map&lt;String, RolodexAddress&gt;, 
	 * where the key is a "friendly name" for the address, like "office", "mom", or <br />
	 * 
	 * <strong>By convention, the user's main address (if any) is at key <em>null</em> of this map.</strong>
	 */
	@OneToMany(mappedBy="cid.userId")
	@MapKey(name="cid.rolodexEntryName")
	public Map<String, RolodexAddress> getRolodex() {
		return this.rolodex;
	}
	public void setRolodex(Map<String, RolodexAddress> rolodex) {
		this.rolodex = rolodex;
	}
	
	
	
	@Transient
	public Address getUserAddress() {
		if (this.getRolodex() != null) {
			return this.getRolodex().get(null);
		} else {
			return null;
		}
	}
	
	
	
	
	/** JPA empty constructor **/
	public User() { }
	
	
	

	
	/**
	 * Promotes a Visitor to User upon "create account procedure".<br />
	 * <strong>Do NOT use to authenticate Visitor as an existing User</strong><br />
	 * Use (TODO: say what method to use to authenticate user) 
	 * instead
	 * 
	 * @param visitor to promote to User
	 * @param login the User wishes to identify with
	 * @param password the User wishes to authenticate with
	 * 
	 * @see (TODO: say what method to use to authenticate user)  
	 */
	public User(Visitor visitor, String login, String password) {
		this.setLogin(login); // FIXME : login uniqueness should be checked first... Would using Spring Security change that? 
		this.setPassword(password);
		
		// TODO: replace (when implemented) with // this.setUserBasket(new UserBasket(visitor.getBasket()));
		// this.setBasket(new UserBasket());
		this.setBasket(new UserBasket(this, visitor.getBasket()));
		
	}
}
