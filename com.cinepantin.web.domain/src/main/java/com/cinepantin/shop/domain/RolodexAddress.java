package com.cinepantin.shop.domain;



import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Transient;



/**
 * RolodexAddress (Domain Entity).<br />
 * Features a <strong>{@link RolodexAddressCid Composite ID}</strong> + {@link AddressFields}.
 * 
 * @see <a href="http://en.wikipedia.org/wiki/Rolodex">Rolodex on Wikipedia</a> : 
 * 		a Rolodex is an address book (well, a rotative, uh, "book"), with a shorter name ;-)
 */
@Entity
public class RolodexAddress 
			extends Address 
{
	
	
	
	
	private RolodexAddressCid cid;
	/** Gets this entity's {@link RolodexAddressCid Composite ID} */
	@EmbeddedId
	public RolodexAddressCid getCid() {
		return cid;
	}
	public void setCid(RolodexAddressCid cid) {
		this.cid = cid;
	}
	
	
	
	/**
	 * Address book addresses are mutable. With user attention if used as ship-to/bill-to in user basket.
	 */
	@Override
	@Transient // though transient in superType, it seems we must annotate this @Transient again. Because of @Override ?
	public Address.Mutability getMutability() {
		logger.info("Went into RolodexAddress.getMutability()");
		
		if (this.isUsedOnBasket()) {
			return Mutability.MUTABLE_WITH_WARNING_USED_IN_BASKET;
		} else {
			return Mutability.MUTABLE;
		}
	}
	
	
	
	private UserBasket basketShippingToMe;
	/** The {@link UserBasket} shipping to this address, if any */
	@OneToOne(mappedBy="shipToAdress", optional=true, fetch=FetchType.LAZY)
	public UserBasket getBasketShippingToMe() {
		return basketShippingToMe;
	}
	public void setBasketShippingToMe(UserBasket basketShippingToMe) {
		this.basketShippingToMe = basketShippingToMe;
	}
	
	
	
	private UserBasket basketBillingToMe;
	/** The {@link UserBasket} billing to this address, if any */
	@OneToOne(mappedBy="billToAdress", optional=true, fetch=FetchType.LAZY)
	public UserBasket getBasketBillingToMe() {
		return basketBillingToMe;
	}
	public void setBasketBillingToMe(UserBasket basketBillingToMe) {
		this.basketBillingToMe = basketBillingToMe;
	}
	
	
	
	
	/**
	 * Tests if a RolodexAddress is used (either as ship-to or bill-to address) on the {@link UserBasket}.
	 * @return true is used at least once on a UserBasket.
	 */
	@Transient 
	public boolean isUsedOnBasket() {
		if ((basketShippingToMe != null) || (basketBillingToMe != null)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	/** JPA empty constructor **/
	public RolodexAddress() {}
	
	
	
	// TODO: is this RolodexAddress constructor needed?
	public RolodexAddress(RolodexAddressCid cid) {
		this.cid = cid;
	}
	
}
