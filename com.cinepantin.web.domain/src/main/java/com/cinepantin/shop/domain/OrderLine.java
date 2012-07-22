package com.cinepantin.shop.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * OrderLine (Domain Entity)
 * Represents a "line" in a {@link Basket} : 1 article, in a given quantity
 *
 */
@Entity
public class OrderLine { // FIXME: What about the relationship with a non-persistent Basket ?!
	
	
	/**
	 * Identifier, mostly technical.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderLineId;
	public int getOrderLineId() {
		return orderLineId;
	}
	protected void setOrderLineId(int orderLineId) {
		this.orderLineId = orderLineId;
	}
	
	
	/**
	 * The {@link Article} that this OrderLine "puts" in the Basket
	 */
	@ManyToOne
	@JoinColumn(name="idArticle")
	private Article article;
	public Article getArticle() {
		return article;
	}
	protected void setArticle(Article article) {
		this.article = article;
	}
	
	
	
	/**
	 * The quantity of {@link Article Articles being put in the Basket}
	 */
	@Basic
	private int quantity;
	public int getQuantity() {
		return quantity;
	}
	protected void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	/*
	public OrderLine() {
		// JPA empty constructor
	}
	*/
	
	
	/**
	 * Mostly used for the class diagram. Is likely to be removed later
	 * 
	 * @param article represented by this OrderLine
	 */
	protected OrderLine( Article article) {
		this.article = article;
	}

}
