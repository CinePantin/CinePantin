package com.cinepantin.shop.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;


/**
 * A Basket is basically a {@link Map}<{@link Article}, {@link Integer}> (what article(s), in what quantity). 
 * 
 * Basket implementations must typically implement a method returning such a map.
 * @author Alain
 *
 */



@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Access(AccessType.PROPERTY)
public class Basket {
	
	
	
	/** Technical default value for order lines map initial capacity **/
	protected int ORDERLINE_MAP_INITIAL_CAPACITY = 4;
	
	
	
	
	/**
	 * A basket is essentially a set of orderlines, each stating which article is wanted, in what quantity.<br />
	 * Therefore <strong>orderlines</strong> is a <tt>Map&lt;Article, Integer&gt</tt> (where <tt>Integer</tt> counts quantity)</tt>. 
	 */
	protected Map<Article, Integer> orderLines; // Integer is the quantity of articles to be added
	@Transient // This doesn't define a mapping. It is the child classes responsibility to define the mapping (if mapping is required)
	public Map<Article, Integer> getOrderLines() {
		if (this.orderLines == null) {
			this.orderLines = new HashMap<Article, Integer>(ORDERLINE_MAP_INITIAL_CAPACITY);
		}
		return this.orderLines;
	}
	public void setOrderLines(Map<Article, Integer> orderLines) { // JPA doesn't allow protected ; should be...
		this.orderLines = orderLines;
	}
	
	
	
	/** JPA empty constructor */
	public Basket() {}
	
	
	
	
	/* =============================================================================================
	 *
	 *									BASKET OPERATIONS 		("Basket API")
	 *									
	 /============================================================================================ */
	
	
	
	/**
	 * One can add 1 (one) Article in a Basket. Synchronized.
	 * @param article The Article to add to the basket. Assumed quantity is 1 (one).
	 */
	@Transient // This is just a plain method. JPA, please don't try to persist me...
	public synchronized void addArticle(Article article) {
		this.modifyArticleQuantity(article, Integer.valueOf( +1 ));
	}
	
	
	
	/**
	 * One can operate on the Article quantity in a Basket. Synchronized.
	 * 
	 * @param article 				The Article whose quantity is to be modifed in the basket.
	 * @param quantityDifference	The amount (positive or negative) of quantity modification.	
	 */
	@Transient // This is just a plain method. JPA, please don't try to persist me...
	public synchronized void modifyArticleQuantity(Article article, Integer quantityDifference) {
		
		// Sanity checks
		if (article == null) {
			throw new IllegalArgumentException("Can't add Article <null> to a Basket!");
		}
		if (quantityDifference == null) {
			throw new IllegalArgumentException("Can't set quantity to <null> articles to a Basket! (" + article.toString() + ")");
		}
		
		// Initial quantity of given article in basket
		Integer initialQuantity = this.getOrderLines().get(article); // null if article not in Map
		if (initialQuantity == null) {
			initialQuantity = Integer.valueOf(0); // then initial quantity is 0
		}
		
		// Computing final quantity
		Integer finalQuantity = initialQuantity + quantityDifference;
		
		// Removing more article than there are in basket?
		if (finalQuantity.compareTo(Integer.valueOf(0)) < 0) {
			// FIXME: decide wether or not to throw an exception in this case
			throw new IllegalArgumentException("Can't set remove <" + Math.abs(quantityDifference) + "> articles from basket, when there are only <" + initialQuantity + "> in given basket.");
			// alternative : set quantityDifference to -initialQuantity
		}
		
		// Unchanged?!
		if (finalQuantity.equals(initialQuantity)) {
			// TODO: log a warn
			System.out.print("Modifying article quantity by zero (<" + quantityDifference.toString() + ">) ?! Weird... Shouldn't you check the code to see if this is really intended?");
			return;
		}
		
		// Perform the operation
		if (finalQuantity.equals(Integer.valueOf(0))) {
			
			// Reached zero ? remove from the Map
			this.getOrderLines().remove(article);
			
		} else {
			
			// Change quantity (creates the article entry in the map if there was none)
			this.getOrderLines().put(article, finalQuantity);
		}
		
	}
	
	
	
	/**
	 * Remove all articles of the given type from the Basket (whatever the previous quantity was).
	 * 
	 * @param article	The type of articles to be removed. (i.e. an {@link Article instance})
	 */
	@Transient // This is just a plain method. JPA, please don't try to persist me...
	public void clearArticle(Article article) {
		if (article == null) {
			throw new IllegalArgumentException("Can't remove articles <null> from basket!");
		}
		this.getOrderLines().remove(article);
	}
	
	
	
	/**
	 * Is the basket empty ?
	 * @return true if empty...
	 */
	@Transient
	public boolean isEmpty() {
		return this.getOrderLines().isEmpty();
	}
	
	
	
	
	
	/**
	 * Makes the basket <strong>empty</strong>. No confirm. Whap!<br />
	 * Think twice^N before use: there's no CTRL+Z on this one ;-)
	 */
	public void clearBasket() {
		this.getOrderLines().clear();
	}
	
	
	

	
}
