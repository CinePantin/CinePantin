package com.cinepantin.shop.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Embeddable;


@Embeddable
public class AddressBookEntryCompositeId 
				implements Serializable 
{


	private static final long serialVersionUID = -4085582811113543996L;
	
	
//	User user;
//	/** An Address belongs to a {@link User} **/
//	@ManyToOne(targetEntity=User.class, optional=false)
//	@JoinColumn(name="login", referencedColumnName="login", nullable=false, updatable=false)
//	public User getUser() {
//		return this.user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
	
	/** An Address belongs to a {@link User} **/
	private Integer userId;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	


	String addressBookEntryName;
	/** An Address is identified by a (unique) friendly name (within a user namespace) **/ //FIXME: since updatable, is this a good ID candidate ?
	@Basic
	// @GeneratedValue(strategy=GenerationType.TABLE)
	public String getAddressBookEntryName() {
		return addressBookEntryName;
	}
	public void setAddressBookEntryName(String addressBookEntryName) {
		this.addressBookEntryName = addressBookEntryName;
	}
	
	
	
	/* Eclipse-generated
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((addressBookEntryName == null) ? 0 : addressBookEntryName.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	
	
	/* Eclipse-generated
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AddressBookEntryCompositeId other = (AddressBookEntryCompositeId) obj;
		if (addressBookEntryName == null) {
			if (other.addressBookEntryName != null) {
				return false;
			}
		} else if (!addressBookEntryName.equals(other.addressBookEntryName)) {
			return false;
		}
		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		return true;
	}
	
}
