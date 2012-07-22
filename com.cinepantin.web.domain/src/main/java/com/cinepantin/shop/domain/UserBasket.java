package com.cinepantin.shop.domain;



import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * UserBasket (Domain Entity), extends (anonymous) Basket (plain Domain class).
 * 
 * Belongs to a registered {@link User}. Is a candidate to {@link Order} promotion upon paiement.
 *
 */
@Entity
@Table(name="BasketsAndOrders")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
@DiscriminatorValue(value="UserBasket")
public class UserBasket extends Basket {
	
	
	
	/** Technical field (PK) **/
	private int basketId;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getBasketId() {
		return basketId;
	}
	protected void setBasketId(int basketId) {
		this.basketId = basketId;
	}
	
	
	
	/*
	 * Misc. overrides for inherited fields that must be anotated as Persitent Entity Fields
	 * 
	 */
	
	@OneToMany(fetch=FetchType.LAZY)
	@Override
	public List<OrderLine> getOrderLineList() {
		return super.getOrderLineList();
	}
	
	
	
	/**
	 * The address to ship to (the address where the articles are to be delivered)
	 */
	private Address shipToAdresse;
	public Address getShipToAdresse() {
		return this.shipToAdresse;
	}
	public void setShipToAdresse(Address shipToAdresse) {
		this.shipToAdresse = shipToAdresse;
	}
	
	
	
	/**
	 * The address to bill to (the address where the envoice must be sent)
	 */
	private Address billToAdresse;
	public Address getBillToAdresse() {
		return billToAdresse;
	}
	protected void setBillToAdresse(Address billToAdresse) {
		this.billToAdresse = billToAdresse;
	}

	
	
	/** JPA Constructor **/
	protected UserBasket() {}

	
	
	/**
	 * Creates a {@link UserBasket} promoted from a {@link Basket}  
	 * @param basket
	 */
	public UserBasket(Basket basket) {
		this.setOrderLineList(basket.getOrderLineList());
	}

}
