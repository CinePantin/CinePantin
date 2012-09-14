package com.cinepantin.shop.domain;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;


/**
 * A site visitor (plain Domain class, NOT a persistent entity)
 *
 */
@MappedSuperclass
public class Visitor {

	private Basket basket;
	/**
	 * <strong>Warning</strong>: Visitor baskets are <strong>NOT</strong> persisted...
	 */
	@Transient
	public Basket getBasket() {
		return this.basket;
	}
	/**
	 * <strong>Warning</strong>: Visitor baskets are <strong>NOT</strong> persisted...
	 */
	protected void setBasket(Basket basket) {
		this.basket = basket;
	}

	public Visitor() {
		// this.basket = new VisitorBasket();
	}

}
