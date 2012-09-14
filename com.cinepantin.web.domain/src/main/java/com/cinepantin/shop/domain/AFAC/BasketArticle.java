/**
 * 
 */
package com.cinepantin.shop.domain.AFAC;

import javax.persistence.Basic;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.cinepantin.shop.domain.Article;
import com.cinepantin.shop.domain.Basket;



/**
 * BasketArticle (plain Domain class **[OR]** Persistent Entity, depending on the Basket it belongs to) represents an "order line" in a {@link Basket}: 
 * 		1 article, in a given quantity
 * 
 * 		(remember the {@link Basket} can be any of its descendants: a visitor basket, a user basket, or a real order).
 * 
 */
@Entity
public class BasketArticle {
	
	
	
	/**
	 * Clustered Key (basket + article) 
	 */
	@EmbeddedId 
	BasketArticleCompositeIdentifier basketArticleCompId;
	

	
	
	/**
	 * The quantity of {@link Article Articles being put in the VisitorBasket}
	 */
	protected Integer quantity;
	@Basic(optional=false) // NOT NULL
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	
	/** JPA empty constructor */
	public BasketArticle() {}
	
	
	
	/**
	 * Diagram generation constructor. TODO: remove
	 */
	public BasketArticle(BasketArticleCompositeIdentifier id) {
		this.basketArticleCompId = id;
	}
}
