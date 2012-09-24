package com.cinepantin.shop.domain;



import java.util.Date;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 * An Order (Domain Entity), defined as : 
 * a {@link UserBasket} 
 * which has been paid for.
 * 
 * Orders MUST be immutable
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name="Orders")
public class Order 
			extends Basket 
{
	
	
	
	/** JPA technical ID */
	private Integer orderId;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	
	
	private User user;
	@ManyToOne(
			optional=false
		)
	@JoinColumn(name="userId", referencedColumnName="userId", nullable=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	/**
	 * The actual basket contents
	 */
	// The following has been greately inspired by the @MapKeyJoinColumn Javadoc
	@Override
	@ElementCollection
	@CollectionTable(name="OrderLine", joinColumns=@JoinColumn(name="orderId"))
	@MapKeyJoinColumn(name="articleId", referencedColumnName="articleId")
	@Column(name="quantity")
	public Map<Article, Integer> getOrderLines() {
		return super.getOrderLines();
	}
	
	
	
	/**
	 * The address to ship to (the address where the articles are to be delivered).
	 * 
	 * TODO: check types (shall we use Address and/or OrderAddress, or both, or trans-type?)
	 */
	private OrderAddress shipToAdress;
	@OneToOne(
			targetEntity=OrderAddress.class,
			optional=false,
			fetch=FetchType.LAZY
			)
	public OrderAddress getShipToAdress() {
		return this.shipToAdress;
	}
	public void setShipToAdress(OrderAddress shipToAdress) {
		this.shipToAdress = shipToAdress;
	}
	
	
	
	
	/**
	 * The address to bill to (the address where the invoice must be sent)
	 * 
	 * TODO: check types (shall we use Address and/or OrderAddress, or both, or trans-type?)
	 */
	private OrderAddress billToAdress;
	@OneToOne(
			targetEntity=OrderAddress.class,
			optional=false,
			fetch=FetchType.LAZY
			)
	public OrderAddress getBillToAdress() {
		return this.billToAdress;
	}
	public void setBillToAdress(OrderAddress billToAdress) {
		this.billToAdress = billToAdress;
	}
	
	
	
	/**
	 * The date the order has been paid for, making it an Order.
	 */
	private Date paiedDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false, insertable=true, updatable=false)
	public Date getPaiedDate() {
		return paiedDate;
	}
	public void setPaiedDate(Date paiedDate) {
		if (this.paiedDate == null) {
			this.paiedDate = paiedDate;
		} else {
			throw new IllegalAccessError("Illegal write access: can't Order.setPaiedDate when paiedDate has already been set...");
		}
	}
	
	
	
	
	//////////////////////		Order is immutable : deactivate Basket API write methods		/////////////////////////////
	
	

	/** <strong>Modifying the contents of an Order is prohibited! DON'T</strong> use this, or get an {@link IllegalAccessError}!
	 * @param illegal Do that and get an exception!, Do you read me?
	 * @throws IllegalAccessError Basket API write methods can't be used on Order (immutable contents) */
	@Override
	public synchronized void addArticle(Article illegal) {
		throw new IllegalAccessError("Modifying the contents of an Order is prohibited.");
	}
	/** <strong>Modifying the contents of an Order is prohibited! DON'T</strong> use this, or get an {@link IllegalAccessError}!
	 * @param illegal Do that and get an exception!, Do you read me?
	 * @param veryIllegal Are you blind? Deaf? 
	 * @throws IllegalAccessError Basket API write methods can't be used on Order (immutable contents) */
	@Override
	public synchronized void modifyArticleQuantity(Article illegal, Integer veryIllegal) {
		throw new IllegalAccessError("Modifying the contents of an Order is prohibited.");
	}
	/** <strong>Modifying the contents of an Order is prohibited! DON'T</strong> use this, or get an {@link IllegalAccessError}!
	 * @param illegal Do that and get an exception!, Do you read me?
	 * @throws IllegalAccessError Basket API write methods can't be used on Order (immutable contents) */
	@Override
	public void clearArticle(Article illegal) {
		throw new IllegalAccessError("Modifying the contents of an Order is prohibited.");
	}
	/** <strong>Modifying the contents of an Order is prohibited! DON'T</strong> use this, or get an {@link IllegalAccessError}!
	 * @throws IllegalAccessError Basket API write methods can't be used on Order (immutable contents) */
	@Override
	public void clearBasket() {
		throw new IllegalAccessError("Modifying the contents of an Order is prohibited.");
	};
	
	
	
	
	/** JPA empty constructor **/
	public Order() {} // JPA doesn't allow, but should be protected...

	

	

	/**
	 * <strong>Checkout method</strong> : creates an {@link Order} promoted from a {@link UserBasket}.<br />
	 * {@link UserBasket#isOrderable()} should really be checked before calling this method, in order to avoid IllegalArgumentException risks.
	 * 
	 * @param userBasket to be promoted to an Order
	 * 
	 * @throws IllegalArgumentException if userBasket.isOrderable() yields false
	 */
	Order(UserBasket userBasket) { // Important: this sensible method should be kept with a PACKAGE visibility...
		
		if (!userBasket.isOrderable()) { // check if UserBasket is OK before promoting
			
			throw new IllegalArgumentException("Trying to order a UserBasket that doesn't satisfy isOrderable() requirements!");
			
		} else { // OK to proceed
			
			this.setUser(userBasket.getUser());
			this.setOrderLines(userBasket.getOrderLines());  // TODO: check if deep copy is needed here.
			this.setBillToAdress(new OrderAddress(userBasket.getBillToAdress()));
			this.setShipToAdress(new OrderAddress(userBasket.getShipToAdress()));
			this.setPaiedDate(new Date( System.currentTimeMillis() )); // TODO: check whether payment dateTime can be returned by payment API
		
		}
	}
	
	
	

}
