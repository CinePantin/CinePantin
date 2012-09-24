package com.cinepantin.shop.domain;


import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;



/**
 * A clustered index <strong>(userId + rolodexEntryName)</strong>
 * 		for use in {@link RolodexAddress}
 *
 */
@Embeddable
@Access(AccessType.PROPERTY)
public class RolodexAddressCid 
				implements Serializable 
{
	private static final long serialVersionUID = -2818031419514229726L;
	
	private Integer userId;
	/** A {@link RolodexAddress} belongs to a {@link User} **/
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	
	
	
	private String rolodexEntryName;
	/** A {@link RolodexAddress} is also identified by its name **/
	public String getRolodexEntryName() {
		return rolodexEntryName;
	}
	public void setRolodexEntryName(String rolodexEntryName) {
		this.rolodexEntryName = rolodexEntryName;
	}
	
	
	
	/** JPA empty constructor **/
	public RolodexAddressCid() {}
	
	
	
	
	
	/* ========================== hashCode & equals, required on composite IDs ========================= */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((rolodexEntryName == null) ? 0 : rolodexEntryName.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	
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
		RolodexAddressCid other = (RolodexAddressCid) obj;
		if (rolodexEntryName == null) {
			if (other.rolodexEntryName != null) {
				return false;
			}
		} else if (!rolodexEntryName.equals(other.rolodexEntryName)) {
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
