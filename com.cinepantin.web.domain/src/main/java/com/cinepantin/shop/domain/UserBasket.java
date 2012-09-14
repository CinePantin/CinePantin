package com.cinepantin.shop.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
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

/**
 * UserBasket (Domain Entity).
 * 
 * Belongs to a registered {@link User}. Is promoted to {@link Order} upon basket checkout (pavement).
 *
 */
@Entity

// @Table(name="BasketsAndOrders")

//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING, length=10)
//@DiscriminatorValue(value="UserBasket")

@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name="Baskets")

public class UserBasket 
			extends Basket 
{



	/** JPA technical identifier (PK) **/
	private Integer basketId;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getBasketId() {
		return basketId;
	}
	protected void setBasketId(Integer basketId) {
		this.basketId = basketId;
	}
	
	
	
	private User user;
	/**
	 * @return The {@link User} this UserBasket belongs to.
	 */
	@OneToOne(targetEntity=User.class, optional=false)
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
//	private List<OrderLine> orderLineList;
//	@OneToMany(
//			targetEntity=com.cinepantin.shop.domain.AFAC.OrderLine.class, // not necessary : "Defaults to the parameterized type of the collection when defined using generics." (says javadoc) 
//			// cascade=ALL, // Entity baskets can't be deleted anyway...
//		    mappedBy="basket", // won't work without...
//		    fetch=FetchType.LAZY
//			)
//	public List<OrderLine> getOrderLineList() {
//		return this.orderLineList;
//	}
//	protected void setOrderLineList(List<OrderLine> orderLineList) {
//		this.orderLineList = orderLineList;
//	}

//	// @Override
////	@ManyToMany(
////			
////			//targetEntity=Address1.class,
////			fetch=FetchType.LAZY
////			)
////	@JoinTable(
////				name="BasketArticle",
////				joinColumns={@JoinColumn(name="basketId")}, // OK
////				inverseJoinColumns={@JoinColumn(name="articleId", referencedColumnName="articleId")} // Nok : creates a FOREIGN KEY (articleId) REFERENCES Address1 (addressId);
////			)
//	@ElementCollection()
//	@CollectionTable(
//	        name="OrderLineMapTable" //,
////	        joinColumns=
////	    			{
////	        			@JoinColumn(name="basketId", referencedColumnName="basketId"),
////        			}
//			)
//	// was (for testing purposes): Map<Article, Address1>
//	public Map<BasketArticleCompositeIdentifier, BasketArticle> getOrderLineMap() { // FOREIGN KEY (orderLineMap_KEY) REFERENCES Article (articleId);
//		return this.orderLineMap;
//	}
	
	
	
	/**
	 * The actual basket contents
	 */
	// The following has been greately inspired by the @MapKeyJoinColumn Javadoc
	@Override
	@ElementCollection
	@CollectionTable(name="BasketLine", joinColumns=@JoinColumn(name="basketId"))
	@MapKeyJoinColumn(name="articleId", referencedColumnName="articleId")
	@Column(name="quantity")
	public Map<Article, Integer> getOrderLines() {
		return super.getOrderLines(); //this.orderLineMap;
	}
	
	
	
	/**
	 * The address to ship to (the address where the articles are to be delivered)
	 */
	private RolodexAddress shipToAdress;
//	@ManyToOne(
//			targetEntity=AddressBookEntry.class,
//			optional=true,
//			fetch=FetchType.LAZY
//			)
//	@JoinColumns(value={
//			@JoinColumn(name="userId", referencedColumnName="userId", insertable=false, updatable=false)
//			, @JoinColumn(name="shipToEntryName", referencedColumnName="addressBookEntryName", insertable=false, updatable=false)
//		}
//	)
	@OneToOne(
			targetEntity=RolodexAddress.class,
			optional=true,
			fetch=FetchType.LAZY
			)
	@JoinColumns(value={
			@JoinColumn(name="userId", referencedColumnName="userId", insertable=false, updatable=false)
			, @JoinColumn(name="shipTo_EntryName", referencedColumnName="rolodexEntryName", insertable=false, updatable=false)
		}
	)
	public RolodexAddress getShipToAdress() {
		return this.shipToAdress;
	}
	public void setShipToAdress(RolodexAddress shipToAdress) {
		this.shipToAdress = shipToAdress;
	}
	
	
	
	/**
	 * The address to bill to (the address where the invoice must be sent)
	 */
	private RolodexAddress billToAdress;
//	@ManyToOne(
//			targetEntity=AddressBookEntry.class,
//			optional=true,
//			fetch=FetchType.LAZY
//			)
//	@JoinColumns(value={
//					@JoinColumn(name="userId", referencedColumnName="userId", insertable=false, updatable=false)
//					, @JoinColumn(name="billToEntryName", referencedColumnName="addressBookEntryName", insertable=false, updatable=false)
//				}
//			)
	@OneToOne(
			targetEntity=RolodexAddress.class,
			optional=true,
			fetch=FetchType.LAZY
			)
	@JoinColumns(value={
					@JoinColumn(name="userId", referencedColumnName="userId", insertable=false, updatable=false)
					, @JoinColumn(name="billTo_EntryName", referencedColumnName="rolodexEntryName", insertable=false, updatable=false)
				}
			)
	public RolodexAddress getBillToAdress() {
		return billToAdress;
	}
	public void setBillToAdress(RolodexAddress billToAdress) {
		this.billToAdress = billToAdress;
	}

	
	
	/** JPA empty constructor **/
	public UserBasket() { // JPA doesn't allow, but should be protected
		// this.setOrderLines(new HashMap<Article, Integer>()); // TODO: empty me
	}

	
	
	/** TODO
	 * Creates a {@link UserBasket} promoted from a {@link VisitorBasket}  
	 * @param visitorBasket
	 */
//	public UserBasket(VisitorBasket visitorBasket) {
//		List<OrderLine> orderLinesListToPromote = visitorBasket.getOrderLineList();
//		this.orderLineList = new ArrayList<UserOrderLine>(orderLinesListToPromote.size());
//		for (OrderLine orderLineToPromote : orderLinesListToPromote) {
//			this.orderLineList.add(new UserOrderLine(orderLineToPromote));
//		}
//	}

}
