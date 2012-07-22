/**
 * 
 */
package com.cinepantin.shop.domain;

/**
 * A site visitor (plain Domain class, NOT a persistent entity)
 *
 */
public class Visitor {

	private Basket basket;
	/**
	 * <strong>Warning</strong>: Visitor baskets are <strong>NOT</strong> persisted...<br />
	 * If you're handling a User (or better), use getUserBasket() instead
	 * 
	 * @see User#getUserBasket(UserBasket)
	 * 
	 */
	public Basket getBasket() {
		return this.basket;
	}
	/**
	 * <strong>Warning</strong>: Visitor baskets are <strong>NOT</strong> persisted...<br />
	 * If you're handling a User (or better), use setUserBasket() instead
	 * 
	 * @see User#setUserBasket(UserBasket)
	 * 
	 */
	protected void setBasket(Basket basket) {
		this.basket = basket;
	}

	public Visitor() {
		this.basket = new Basket();
	}

}
