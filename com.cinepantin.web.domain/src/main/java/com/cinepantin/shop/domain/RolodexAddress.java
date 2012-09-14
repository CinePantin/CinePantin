package com.cinepantin.shop.domain;

import javax.persistence.Basic;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.cinepantin.shop.domain.Address.Mutability;



/**
 * RolodexAddress (Domain Entity).<br />
 * <strong>Generated ID</strong> + {@link AddressFields}.
 * 
 * @see <a href="http://en.wikipedia.org/wiki/Rolodex">Rolodex on Wikipedia</a> : a Rolodex is an address book (with a shorter name ;-))
 */
@Entity
// @Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
// @Embeddable
// @IdClass(value=com.cinepantin.shop.domain.RolodexAddressCid.class)
public class RolodexAddress extends Address {
	
	
//	/** JPA technical PK **/
//	private Integer addressId;
//	@Id
//	@GeneratedValue(strategy=GenerationType.TABLE)
//	public Integer getAddressId() {
//		return addressId;
//	}
//	public void setAddressId(Integer addressId) {
//		this.addressId = addressId;
//	}
	
	
	
	/**
	 * Address book addresses are mutable. With user attention if used as ship-to/bill-to in user basket.
	 */
	@Override
	@Transient // doesn't need to be persisted // TODO: unnecessary (inherited)?
	public Address.Mutability getMutability() { // TODO: implement when this.isUsedOnBasket() is available
		//// If used on basket 
		// return Mutability.MUTABLE_WITH_WARNING_USED_IN_BASKET
		// } else {

		logger.info("Went into RolodexAddress.getMutability()");
		return Mutability.MUTABLE;
		// }
	}
	
	
	private UserBasket basketShippingToMe;
	@OneToOne(mappedBy="shipToAdress", optional=true, fetch=FetchType.LAZY)
	public UserBasket getBasketShippingToMe() {
		return basketShippingToMe;
	}
	public void setBasketShippingToMe(UserBasket basketShippingToMe) {
		this.basketShippingToMe = basketShippingToMe;
	}
	
	
	
	private UserBasket basketBillingToMe;
	@OneToOne(mappedBy="billToAdress", optional=true, fetch=FetchType.LAZY)
	public UserBasket getBasketBillingToMe() {
		return basketBillingToMe;
	}
	public void setBasketBillingToMe(UserBasket basketBillingToMe) {
		this.basketBillingToMe = basketBillingToMe;
	}
	
	
	
	
//	/** An Address belongs to a {@link User} **/
//	@ManyToOne // (targetEntity=User.class, optional=false, fetch=FetchType.LAZY)
//	// @JoinColumn(name="ownerLogin", referencedColumnName="login", nullable=false, updatable=false)
//	private User user;
//	public User getUser() {
//		return this.user;
//	}
//	protected void setUser(User user) {
//		this.user = user;
//	}
	
	
	private RolodexAddressCid cid;
	@EmbeddedId
	public RolodexAddressCid getCid() {
		return cid;
	}
	public void setCid(RolodexAddressCid cid) {
		this.cid = cid;
	}
	
	
	
//	private String rolodexEntryName;
//	@MapsId("rolodexEntryName")
//	@Basic(optional=true)
//	public String getRolodexEntryName() {
//		return rolodexEntryName;
//	}
//	public void setRolodexEntryName(String rolodexEntryName) {
//		this.rolodexEntryName = rolodexEntryName;
//	}
//	
//	
//	
//	private Integer userId;
//	@MapsId("userId")
//	@ManyToOne
//	public Integer getUserId() {
//		return userId;
//	}
//	public void setUserId(Integer userId) {
//		this.userId = userId;
//	}
	
	
	
	
	
	
	
	// TODO: not needed as inherited?
//	private AddressFields addressFields;
//	@Embedded
//	public AddressFields getAddressFields() {
//		return addressFields;
//	}
//	public void setAddressFields(AddressFields addressFields) {
//		this.addressFields = addressFields;
//	}
	
	
	
	
	
	
	
	
	/** JPA empty constructor **/
	public RolodexAddress() {}
	
	
	

	public RolodexAddress(RolodexAddressCid cid) {
		this.cid = cid;
	}
	
}
