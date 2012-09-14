package com.cinepantin.shop.domain;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

import com.cinepantin.shop.domain.Address.Mutability;



/**
 * OrderAddress (Domain Entity) is an immutable address used on an order<br />
 * (either as <strong>shipTo</strong> or <strong>billTo</strong> address).<br /><br />
 * Features: generated ID + @Embedded AddressFields
 *
 */
@Entity
// @Embeddable
// @Inheritance(strategy=InheritanceType.TABLE_PER_CLASS) // TODO: this should not be required...
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
	@Transient // doesn't need to be persisted // TODO: unnecessary (inherited)?
	public Address.Mutability getMutability() {
		logger.info("Went into OrderAddress.getMutability()");
		return Mutability.IMMUTABLE;
		// }
	}
	
	
	
	// *************     private AddressFields addressFields // IS NOT NEEDED, as it is inherited!    *****************/
//	private AddressFields addressFields;
//	@Embedded
//	public AddressFields getAddressFields() {
//		return addressFields;
//	}
//	public void setAddressFields(AddressFields addressFields) {
//		this.addressFields = addressFields;
//	}
	
	
	
	
	/** JPA empty constructor **/
	public OrderAddress() {}
	
	
	
}
