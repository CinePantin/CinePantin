package com.cinepantin.shop.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embedded;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Access(AccessType.PROPERTY)
public class Address {
	
	protected Logger logger = LoggerFactory.getLogger(RolodexAddress.class);
	
	
	
	public enum Mutability {
		MUTABLE(true, "Address book addresses can be modified, when they are not used in the user's basket."),
		MUTABLE_WITH_WARNING_USED_IN_BASKET(true, "User should be warned before modifying an address that is used in his/her basket."),
		IMMUTABLE(false, "Can't modify an address used in an order.")
		;
		
		private final boolean mutable;
		public boolean isMutable() {
			return this.mutable;
		}
		
		private final String details; 
		public String getDetails() {
			return this.details;
		}
		
		private Mutability(boolean isMutable, String explaination) {
			this.mutable = isMutable;
			this.details = explaination;
		}
	}
	
	
	
	private Mutability mutability;
	@Transient
	public Mutability getMutability() {
		return this.mutability;
	}
	@Transient
	public boolean isMutable() {
		return (this.getMutability().isMutable());
	}
	
	
	


	private AddressFields addressFields;
	/** An Address holds {@link AddressFields}. */
	@Embedded
	public AddressFields getAddressFields() {
		return this.addressFields;
	}
	public void setAddressFields(AddressFields addressFields) {
		this.addressFields =addressFields;
	}
	
	
	
	
	
	/** JPA empty constructor */
	public Address() {}
	
	
	
}
