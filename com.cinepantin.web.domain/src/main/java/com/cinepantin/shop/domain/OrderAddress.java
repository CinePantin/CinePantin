package com.cinepantin.shop.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;



/**
 * OrderAddress (Domain Entity) is an immutable address used on an order<br />
 * (either as <strong>shipTo</strong> or <strong>billTo</strong> address).<br /><br />
 * Features: generated ID + @Embedded AddressFields
 *
 */
@Entity
public class OrderAddress extends Address {
	
	
	
	/** JPA technical PK **/
	private Integer addressId;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	
	
	
	/**
	 * Addresses used on order (bill-to, ship-to) are <strong>NOT<strong> mutable.
	 */
	@Override
	@Transient 	// doesn't need to be persisted -- still necessary to be explicitly mentioned, 
				// even though inherited and already @Transient in @MappedSuperclass Address...
	public Address.Mutability getMutability() {
		logger.info("Went into OrderAddress.getMutability()");
		return Mutability.IMMUTABLE;
	}
	
	
	
	/** JPA empty constructor **/
	public OrderAddress() {}
	
	
	
	/**
	 * Creates an OrderAddress from a RolodexAddress.
	 * 
	 * @param rolodexAdress to promote to OrderAddress
	 */
	public OrderAddress(RolodexAddress rolodexAdress) {
		this.setAddressFields(rolodexAdress.getAddressFields());
	}
	
	
	
}
