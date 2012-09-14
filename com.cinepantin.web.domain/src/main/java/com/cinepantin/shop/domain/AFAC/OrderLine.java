package com.cinepantin.shop.domain.AFAC;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cinepantin.shop.domain.Article;
import com.cinepantin.shop.domain.Basket;
import com.cinepantin.shop.domain.UserBasket;



/**
 * OrderLine (plain Domain class **[OR]** Persistent Entity, depending on the Basket it belongs to)
 * Represents a "line" in a {@link Basket} : 1 article, in a given quantity
 *
 */
@Entity
public class OrderLine {
	
	
	/**
	 * JPA technical identifier (PK).
	 */
	private int orderLineId;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getOrderLineId() {
		return orderLineId;
	}
	protected void setOrderLineId(int orderLineId) {
		this.orderLineId = orderLineId;
	}
	
	
	/** TODO (or not ?)
	 * The {@link Basket} this OrderLine belongs to
	 */
	protected Basket basket;
	@ManyToOne(
			targetEntity=com.cinepantin.shop.domain.UserBasket.class,
			optional=true
			)
	@JoinColumn( name="basketId", referencedColumnName="basketId") // SQL : OrderLine.Basket_Id
	public Basket getBasket() {
		return basket;
	}
	public void setBasket(Basket basket) {
		this.basket = basket;
	}
	
	
	

	/**
	 * The {@link Article} that this OrderLine "puts" in the VisitorBasket
	 */
	protected Article article;
	@ManyToOne(optional=false)
	@JoinColumn(name="articleId")
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	
	
	/**
	 * The quantity of {@link Article Articles being put in the VisitorBasket}
	 */
	protected int quantity;
	@Basic
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	/** JPA empty constructor */
	public OrderLine() {}
	
	
	/**
	 * Mostly used for the class diagram. Is likely to be removed later
	 * 
	 * @param article represented by this OrderLine
	 */
	protected OrderLine( Article article) {
		this.article = article;
	}
	

}
