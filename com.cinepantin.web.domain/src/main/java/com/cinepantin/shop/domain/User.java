package com.cinepantin.shop.domain;

import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;


/**
 * A User (Domain Entity) is a {@link Visitor} with a login.<br /> 
 * Could be named <em>RegisteredUser</em>, as opposed to an anonymous {@link Visitor}.
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

	
	/**
	 * hides (sub-types) Basket super.basket
	 */
	protected UserBasket basket; // Override type defined in parent class
	/**
	 * Return this user's {@link UserBasket} (persistant entity).
	 */
	@Override
	@OneToOne(
			targetEntity=UserBasket.class
			, mappedBy="user"
			, optional=false
			, fetch=FetchType.LAZY
		) 
	public UserBasket getBasket() { // Hides Basket super.getBasket (defines mapping with UserBasket entity, when Basket is no entity)
		return this.basket;
	}
	public void setBasket(UserBasket userBasket) {
		this.basket = userBasket;
	}


	
	
	private List<Order> orders;
	@OneToMany(
			// mappedBy="userId"
			mappedBy="user"
	)
	public List<Order> getOrders() {
		return this.orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	

	
	

//	private Map<String, Address1> addressList;
//	@ElementCollection(fetch=FetchType.LAZY, targetClass=Address1.class)
//	@CollectionTable(
//			name="AddressBook_Elmt_Coll_of_A1", 
//			joinColumns=@JoinColumn(name="userId") 
//			//, uniqueConstraints=@UniqueConstraint(columnNames={"friendlyName"}) // TODO: decide if we uncomment this
//		)
//	// @JoinColumn(name="addressId", referencedColumnName="addressId") // Nok : ne produit aucun effet dans ce contexte
//	// @Column(name="articleId") // Nok : ne produit aucun effet dans ce contexte
//	// @MapKey(name="addressName") // The String (map key) references field "addressName" of class Address1 (map malue)
//	@MapKeyColumn(name="friendlyName") // The String (map key) will be created as a new column in the association table. Better suited performance-wise ?
//	public Map<String, Address1> getAddressList() {
//		return addressList;
//	}
//	void setAddressList(Map<String, Address1> addressList) {
//		this.addressList = addressList;
//	}
//
//	
//
//	// TODO: JPA tests, to be removed later
//	private Map<String, Address2> addressList2;
//	@ElementCollection(fetch=FetchType.LAZY, targetClass=Address2.class)
//	@CollectionTable(name="Address2", joinColumns=@JoinColumn(name="userId"))//, joinColumns=@JoinColumn(name="userId"))
//	@MapKeyColumn(name="friendlyName")
//	// @MapKey(name="friendlyNameEmb") // Nok => "Associated class not found: com.cinepantin.shop.domain.Address2" (sans doute car @Embeddable, pas @Entity)
//	public Map<String, Address2> getAddressList2() {
//		return this.addressList2;
//	}
//	protected void setAddressList2(Map<String, Address2> addressList2) {
//		this.addressList2 = addressList2;
//	}
	
	
//	//////////////////     YYYEEEEEEEEEEEEEEEEEEEEEEEEEEEESSSSSSSSSSSSSSSSSSSS!!!!!!!!		YES!		YES!		YES!     ********************************!!!!!
//	private Map<String, AddressBookEntry> addressBookEntries;
//	@OneToMany(mappedBy="cid.userId")
//	@MapKey(name="cid.addressBookEntryName")
//	public Map<String, AddressBookEntry> getAddressBookEntries() {
//		return this.addressBookEntries;
//	}
//	protected void setAddressBookEntries(Map<String, AddressBookEntry> addressList3) {
//		this.addressBookEntries = addressList3;
//	}
	
	
	


//	private Map<String, RolodexAddress> rolodex;	
//	@ElementCollection(targetClass=RolodexAddress.class, fetch=FetchType.LAZY)
//	@CollectionTable(
//			// name="AddressBook_Elmt_Coll_of_A4" 
//			// , 
//			joinColumns={
//					@JoinColumn(name="owner_userId", referencedColumnName="userId")
//			}
//		)
//	@MapKeyColumn(name="entryName")
//	public Map<String, RolodexAddress> getRolodex() {
//		return this.rolodex;
//	}
//	protected void setRolodex(Map<String, RolodexAddress> rolodex) {
//		this.rolodex = rolodex;
//	}
//	
//	
//	
//	
//	
	
	private Map<String, RolodexAddress> rolodex;	
	/**
	 * As long as generated schema is concerned, this mapping is almost identical to the previous,
	 * except we have control of the FK name in the intermediate table 
	 * @return
	 */
//	@OneToMany(fetch=FetchType.LAZY)
//	@JoinTable(
//			name="Rolodex"
//			, 
//			joinColumns={ @JoinColumn(name="userId", referencedColumnName="userId") }
//			, inverseJoinColumns={ @JoinColumn(name="addressId", referencedColumnName="addressId") }
//		)
//	@MapKeyColumn(name="entryName")

//	@ElementCollection(fetch=FetchType.LAZY)
//	// @CollectionTable(name="Rolodex", joinColumns={@JoinColumn(name="userId")})//, joinColumns=@JoinColumn(name="userId"))
//	@JoinTable(
//				name="Rolodex"
//				, 
//				joinColumns={ 
//						@JoinColumn(name="userId", referencedColumnName="userId") 
//					}
//				, inverseJoinColumns={
//						@JoinColumn(name="addressId", referencedColumnName="addressId")
//						// , @JoinColumn(name="rolodexEntryName", referencedColumnName="rolodexEntryName")
//					}
//			)
//	@MapKeyJoinColumn(name="rolodexEntryName")
//	// @MapKey(name="rolodexEntryName")
	

	// @OneToMany(mappedBy="cid.userId")
	/**
	 * A user's Rolodex is a Map&lt;String, RolodexAddress&gt;, where the key is a "friendly name" for the address, like "office", "mom", or <br />
	 * 
	 * By convention, the user's main address (if any) is at key <em>null</em> of this map.
	 */
	@OneToMany(mappedBy="cid.userId")
	@MapKey(name="cid.rolodexEntryName")
	public Map<String, RolodexAddress> getRolodex() {
		return this.rolodex;
	}
	protected void setRolodex(Map<String, RolodexAddress> rolodex) {
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
	protected User() {
		// this.setUserBasket(new UserBasket());
		// this.setBasket(new UserBasket());
	}
	

	
	public User(Visitor visitor, String login, String password) {
		this.setLogin(login);
		this.setPassword(password);
		this.setBasket(new UserBasket()); // TODO: replace (when implemented) with // this.setUserBasket(new UserBasket(visitor.getBasket()));

		// TODO this.setBasket(new UserBasket((VisitorBasket)visitor.getBasket()));
	}
}
