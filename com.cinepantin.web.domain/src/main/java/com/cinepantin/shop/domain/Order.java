/**
 * 
 */
package com.cinepantin.shop.domain;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * An order (Domain Entity), defined as : 
 * a {@link UserBasket} 
 * which belongs to a {@link Customer} 
 * and has been paid for.
 * 
 * Orders MUST be immutable
 *
 */
@Entity
@DiscriminatorValue(value="Order")
@TableGenerator( // We will need a table generator to populate the "orderId" field 
    name="nextOrderId",
    table="ID_GEN",
    pkColumnName="GEN_KEY",
    valueColumnName="GEN_VALUE",
    pkColumnValue="ORDER_ID",
    allocationSize=1)
public class Order extends UserBasket {

	
	
	// @Id // FIXME: Weird : the @Id is inherited... @GeneratedValue should be enough... 
	// Actually caused an error : java.lang.ClassCastException: org.hibernate.mapping.SingleTableSubclass cannot be cast to org.hibernate.mapping.RootClass
	@GeneratedValue(strategy=GenerationType.TABLE, generator="nextOrderId")
	private int orderId;
	public int getOrderId() {
		return orderId;
	}
	protected void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	

	@Temporal(TemporalType.TIMESTAMP)
	private Date paiedDate;
	public Date getPaiedDate() {
		return paiedDate;
	}
	protected void setPaiedDate(Date paiedDate) {
		if (this.paiedDate == null) {
			this.paiedDate = paiedDate;
		} else {
			throw new IllegalAccessError("Illegal write access: can't Order.setPaiedDate when paiedDate has already been set...");
		}
	}
	
	
	
	/** JPA empty constructor **/
	protected Order() {}

	
	
	/**
	 * Creates an {@link Order} promoted from a {@link UserBasket} 
	 * 
	 * @param userBasket to be promoted to an Order
	 */
	Order(UserBasket userBasket) { // Important: this sensible method should be kept with a PACKAGE visibility...
		this.setBasketId(userBasket.getBasketId());
		this.setOrderLineList(userBasket.getOrderLineList());
		this.setBillToAdresse(userBasket.getBillToAdresse());
		this.setShipToAdresse(userBasket.getShipToAdresse());
		this.setPaiedDate(new Date( System.currentTimeMillis() )); // TODO: check whether payment dateTime can be returned by payment API
	}


}
