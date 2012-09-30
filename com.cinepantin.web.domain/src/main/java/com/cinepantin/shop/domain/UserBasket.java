package com.cinepantin.shop.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
import javax.persistence.JoinColumns;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;




/**
 * UserBasket (Domain Entity).
 * 
 * Belongs to a registered {@link User}. Is promoted to {@link Order} upon basket checkout (payment).
 *
 */
@Entity
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
	@OneToOne(
			targetEntity=User.class
			, optional=false
		)
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	/**
	 * The actual basket contents
	 */
	// The following has been greatly inspired by the @MapKeyJoinColumn Javadoc
	@Override
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="BasketLine", joinColumns=@JoinColumn(name="basketId"))
	@MapKeyJoinColumn(name="articleId", referencedColumnName="articleId")
	@Column(name="quantity", insertable=true, updatable=true, nullable=false)
	// @Cascade({CascadeType.ALL,CascadeType.PERSIST,CascadeType.MERGE}) // Hibernate-only anotation ; thought it would be necessary, but it seems it isn't (so far)
	public Map<Article, Integer> getOrderLines() {
		return super.getOrderLines(); //this.orderLineMap;
	}
	
	
	
	/**
	 * The address to ship to (the address where the articles are to be delivered)
	 */
	private RolodexAddress shipToAdress;
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
	public UserBasket() {}
	
	
	
	
	/**
	 * Creates the UserBasket belonging to a given {@link User}
	 * @param user to whom the basket will belong
	 */
	public UserBasket(User user) {
		this();
		this.setUser(user);
	}

	
	
	/**
	 * Creates a {@link UserBasket} promoted from a {@link VisitorBasket}  
	 * @param user to whom this basket will belong
	 * @param visitorBasket to promote to UserBasket
	 */
	public UserBasket(User user, Basket visitorBasket) {
//		List<OrderLine> orderLinesListToPromote = visitorBasket.getOrderLines();
//		this.orderLineList = new ArrayList<UserOrderLine>(orderLinesListToPromote.size());
//		for (OrderLine orderLineToPromote : orderLinesListToPromote) {
//			this.orderLineList.add(new UserOrderLine(orderLineToPromote));
//		}
		this(user);
		this.setOrderLines(visitorBasket.getOrderLines()); // TODO: check if deep copy is needed here.
	}

	
	
	
	
	// TODO: move business rule isOrderable() outside DAO object UserBasket...
	@Transient
	public boolean isOrderable() {
		if (this.getUser() == null) { throw new IllegalStateException("UserBasket belongs to no User! Programming error is likely, as a UserBasket should always belong to a User..."); }
		if (this.getShipToAdress() == null) { return false; }
		if (this.getBillToAdress() == null) { return false; }
		if (this.isEmpty()) { 
			return false; 
		} else {
			if (!isOrderableContentsWise()) { return false; }
		}
		return true;
	}
	
	
	@Transient
	public boolean isOrderableContentsWise() {
		for (Entry<Article, Integer> orderLine : this.getOrderLines().entrySet()) {
			Article article = orderLine.getKey();
			if (article instanceof PhysicalArticle) {
				Integer orderQuantity = orderLine.getValue();
				Integer availableQuantity = ((PhysicalArticle) article).getStockQuantity();
				if (orderQuantity.compareTo( availableQuantity ) < 0) { 
					return false; // too bad we can't tell what article make us yield false....
				}
			} // other article subtypes will require: elseif article instanceof othersubtype...
			// TODO: think QuantityDependantOrderable interface, with a method like: boolean canSatisfyOrderOf(Integer quantity)
		}
		return true;
	}
	
	
	
	/**
	 * Get a Map of Article that can't be ordered, or <code>null</code> if all Articles in UserBasket can be ordered.
	 * @return If there are unorderable Articles, a <code>Map&lt;Article, Integer&gt;</code> 
	 * 				where <ul>
	 * 						<li><code>Article</code> are order members for which order quantity exceeds stocks</li> 
	 * 						<li><code>Integer</code> is the available stock of said Article.</li>
	 * 				</ul>
	 * 				<code>null</code> if all article quantities in order can be satisfied by stock.
	 */
	@Transient
	public Map<Article, Integer> getUnOrderableArticles() {
		Map<Article, Integer> ret = new HashMap<Article, Integer>(4); 
		for (Entry<Article, Integer> orderLine : this.getOrderLines().entrySet()) {
			Article article = orderLine.getKey();
			if (article instanceof PhysicalArticle) {
				Integer orderQuantity = orderLine.getValue();
				Integer availableQuantity = ((PhysicalArticle) article).getStockQuantity();
				if (orderQuantity.compareTo( availableQuantity ) < 0) { 
					ret.put(article, availableQuantity);
				}
			}
		}
		if (ret.size() > 0) {
			return ret;
		} else {
			return null;
		}
	}
	
	
	
}
