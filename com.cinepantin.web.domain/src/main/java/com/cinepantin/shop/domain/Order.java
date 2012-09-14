package com.cinepantin.shop.domain;

import java.util.Date;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * An order (Domain Entity), defined as : 
 * a {@link UserBasket} 
 * which 
 * 		<del>belongs to a {@link Customer} 
 * 		and</del>
 * 		has been paid for.
 * 
 * Orders MUST be immutable
 *
 */
@Entity
// @DiscriminatorValue(value="Order")

@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name="Orders")

//@TableGenerator( // We will need a table generator to populate the "orderId" field 
//    name="nextOrderId",
//    table="ID_GEN",
//    pkColumnName="GEN_KEY",
//    valueColumnName="GEN_VALUE",
//    pkColumnValue="ORDER_ID",
//    allocationSize=1)
public class Order 
			extends Basket 
{



	// @Id // FIXME: Weird : the @Id is inherited... @GeneratedValue should be enough... 
	// Actually caused an error : java.lang.ClassCastException: org.hibernate.mapping.SingleTableSubclass cannot be cast to org.hibernate.mapping.RootClass
	// @GeneratedValue(strategy=GenerationType.TABLE, generator="nextOrderId")  // FIXME : won't work, since JPA specs only require its support on @Id fields... too bad
	
	/** JPA technical ID */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer orderId;
	public Integer getOrderId() {
		return orderId;
	}
	protected void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	
	// Override User to Customer if Customer extends User.
	private Integer userId;
	/**
	 * @return The userId of the {@link User} who passed that order.
	 */
	@ManyToOne(
//			targetEntity=User.class
//			, 
			optional=false
		)
	@JoinColumn(name="userId", referencedColumnName="userId", nullable=false)
	public Integer getUserId() {
		return this.userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	/**
	 * The actual basket contents
	 */
	// The following has been greately inspired by the @MapKeyJoinColumn Javadoc
	@Override
	@ElementCollection
	@CollectionTable(name="OrderOrderLine", joinColumns=@JoinColumn(name="orderId")) // TODO: rename this table and the corresponding UserBasket OrderLine table
	@MapKeyJoinColumn(name="articleId", referencedColumnName="articleId")
	@Column(name="quantity")
	public Map<Article, Integer> getOrderLines() {
		return super.getOrderLines(); // TODO: check if this (using super) is correct
	}
	
	
	
	
//	/**
//	 * The address to ship to (the address where the articles are to be delivered).
//	 * 
//	 * TODO: check types (shall we use Address and/or OrderAddress, or both, or trans-type?)
//	 */
//	private OrderAddress shipToAdress;
//	@OneToOne(
//			targetEntity=OrderAddress.class,
//			optional=false,
//			fetch=FetchType.LAZY
//			)
//	
////	@JoinColumns(value={
//////			@JoinColumn(name="userId", referencedColumnName="userId", insertable=true, updatable=false)
//////			, 
////			@JoinColumn(name="shipTo_addressId", referencedColumnName="addressId", insertable=true, updatable=false)
////		}
////	)
//	public OrderAddress getShipToAdress() {
//		return this.shipToAdress;
//	}
//	public void setShipToAdress(OrderAddress shipToAdress) {
//		this.shipToAdress = shipToAdress;
//	}
	
	
	
	
//	/**
//	 * The address to bill to (the address where the invoice must be sent)
//	 * 
//	 * TODO: check types (shall we use Address and/or OrderAddress, or both, or trans-type?)
//	 */
//	private OrderAddress billToAdress;
//	@OneToOne(
//			targetEntity=OrderAddress.class,
//			optional=false,
//			fetch=FetchType.LAZY
//			)
//	@JoinColumns(value={
//			@JoinColumn(name="userId", referencedColumnName="userId", insertable=true, updatable=false)
//			, @JoinColumn(name="billTo_addressId", referencedColumnName="addressId", insertable=true, updatable=false)
//		}
//	)
//	public OrderAddress getBillToAdress() {
//		return this.billToAdress;
//	}
//	public void setBillToAdress(OrderAddress billToAdress) {
//		this.billToAdress = billToAdress;
//	}
	
	
	
	
	
	
	
	
	
	
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
	public Order() {} // JPA doesn't allow, but should be protected...

	

	
	// TODO: implement
//	/**
//	 * Creates an {@link Order} promoted from a {@link UserBasket} 
//	 * 
//	 * @param userBasket to be promoted to an Order
//	 */
//	Order(UserBasket userBasket) { // Important: this sensible method should be kept with a PACKAGE visibility...
//		// this.setBasketId(userBasket.getBasketId());
//		this.setOrderLines(userBasket.getOrderLines());
//		// this.setBillToAdress(userBasket.getBillToAdress()); // TODO: implement promotion
//		// this.setShipToAdress(userBasket.getShipToAdress()); // TODO: implement promotion
//		this.setPaiedDate(new Date( System.currentTimeMillis() )); // TODO: check whether payment dateTime can be returned by payment API
//	}


	
	/**
	 * Order contents are immutable
	 */
	@Override
	public synchronized void addArticle(Article illegal) {
		throw new IllegalAccessError("Modifying the contents of an Order is prohibited.");
	}
	@Override
	public synchronized void modifyArticleQuantity(Article illegal, Integer veryIllegal) {
		throw new IllegalAccessError("Modifying the contents of an Order is prohibited.");
	}
	@Override
	public void clearArticle(Article illegal) {
		throw new IllegalAccessError("Modifying the contents of an Order is prohibited.");
	}

}
