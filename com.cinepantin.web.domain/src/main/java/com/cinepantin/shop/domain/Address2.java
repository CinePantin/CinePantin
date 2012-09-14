package com.cinepantin.shop.domain;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;



/**
 * (@Embeddable) Address2.<br />
 * Contains just @Embedded AddressFields, no ID (will be provided by mapping of Map when used)
 *
 */
@Embeddable // allows to have no ID (will be provided by mapping of Map when used)
public class Address2 extends Address {

//	private Integer a2id;
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	public Integer getA2id() {
//		return a2id;
//	}
//	public void setA2id(Integer a2id) {
//		this.a2id = a2id;
//	}
	
	
	
	private AddressFields addressFields;
	@Embedded
	public AddressFields getAddressFields() {
		return addressFields;
	}
	public void setAddressFields(AddressFields addressFields) {
		this.addressFields = addressFields;
	}
	
	
	
//	private String unPremierChamp;
//	public String getUnPremierChamp() {
//		return unPremierChamp;
//	}
//	public void setUnPremierChamp(String unPremierChamp) {
//		this.unPremierChamp = unPremierChamp;
//	}
//	
//	
//	
//	private String unAutreChamp;
//	public String getUnAutreChamp() {
//		return unAutreChamp;
//	}
//	public void setUnAutreChamp(String unAutreChamp) {
//		this.unAutreChamp = unAutreChamp;
//	}
	
	
	
}
