package com.cinepantin.shop.domain;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * AddressBookEntry (Domain Entity)<br />
 * Features an @EmbeddedId ({@link AddressBookEntryCompositeId}), with fields matching those of the composite id.<br />
 * + AddressFields
 */
@Entity
public class AddressBookEntry extends Address {

	
	
	/**
	 * Address book addresses are mutable. With user attention if used as ship-to/bill-to in user basket.
	 */
	@Override
	@Transient // doesn't need to be persisted
	public Address.Mutability getMutability() { // TODO: implement when this.isUsedOnBasket() is available
		//// If used on basket 
		// return Mutability.MUTABLE_WITH_WARNING_USED_IN_BASKET
		// } else {
		return Mutability.MUTABLE;
		// }
	}
	
	
	
	private AddressBookEntryCompositeId cid;
	@EmbeddedId
	public AddressBookEntryCompositeId getCid() {
		return cid;
	}
	public void setCid(AddressBookEntryCompositeId cid) {
		this.cid = cid;
	}
	
	
	
//	private User user;
//	@MapsId("userId")
//	@ManyToOne(optional=false, fetch=FetchType.LAZY)
//	public User getUser() {
//		return this.user;
//	}
//	protected void setUser(User addressBookOwner) {
//		this.user = addressBookOwner;
//	}
	
	
	
//	private Integer userId;
//	@MapsId("userId")
//	@ManyToOne(optional=false, fetch=FetchType.LAZY)
//	public Integer getUserId() {
//		return this.userId;
//	}
//	protected void setUserId(Integer userId) {
//		this.userId = userId;
//	}
	
	
	
	private AddressFields addressFields;
	@Override
	@Embedded
	public AddressFields getAddressFields() {
		return addressFields;
	}
	public void setAddressFields(AddressFields addressFields) {
		this.addressFields = addressFields;
	}
	
	
	/** JPA empty constructor */
	public AddressBookEntry() {}
	
//	public AddressBookEntry(AddressBookEntryCompositeId cid, AddressFields addressFields) {
//		this.cid = cid;
//		this.addressFields = addressFields;
//	}
	
	
}
