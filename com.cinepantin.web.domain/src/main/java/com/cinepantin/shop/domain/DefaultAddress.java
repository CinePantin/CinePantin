package com.cinepantin.shop.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


/**
 * Domain Entity (extends Address1)
 * @author alain
 *
 */
// @Entity
public class DefaultAddress 
//				extends Address1 
{
	
	
	private String TODO;
	

//	@OneToOne(targetEntity=Customer.class, mappedBy="defaultAddress")
//    @JoinColumn(
//        	name="login", unique=true, nullable=false, updatable=false)
//	private Customer customer;
//	public Customer getCustomer() {
//		return customer;
//	}
//	protected void setCustomer(Customer customer) {
//		this.customer = customer;
//	}


	/** JPA constructor **/
	public DefaultAddress() {}

}
