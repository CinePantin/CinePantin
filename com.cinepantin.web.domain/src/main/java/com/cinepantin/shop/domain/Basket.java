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

public abstract class Basket {

	protected int ORDERLINE_MAP_INITIAL_CAPACITY = 4;
	
	
	protected Map<Article, Integer> orderLines; // Integer is Article.articleId
//	public abstract Map<Article, Integer> getOrderLineMap();
	@Transient
	public Map<Article, Integer> getOrderLines() {
		if (this.orderLines == null) {
			this.orderLines = new HashMap<Article, Integer>(ORDERLINE_MAP_INITIAL_CAPACITY);
		}
		return this.orderLines;
	}
	public void setOrderLines(Map<Article, Integer> orderLines) { // JPA doesn't allow protected ; should be...
		this.orderLines = orderLines;
	}
	//
	// @see @ElementCollection
	// @see @CollectionTable
//	/**
//	 * Baskets can return a List<OL extends OrderLine>
//	 * @return
//	 */
//	@OneToMany(
//			targetEntity=com.cinepantin.shop.domain.UserOrderLine.class, // not necessary : "Defaults to the parameterized type of the collection when defined using generics." (says javadoc) 
//			// cascade=ALL, // Entity baskets can't be deleted anyway...
//            mappedBy="basket", // won't work without...
//            fetch=FetchType.LAZY
//			)
	// @Transient

//	protected List<OrderLine> orderLineList;
//	public abstract List<OrderLine> getOrderLineList();
//	protected void setOrderLineList(List<OrderLine> orderLineList) {
//		this.orderLineList = orderLineList;
//	}

//	public Basket() {
//		this.orderLineList = new ArrayList<OrderLine>();
//	}


	
	/**
	 * One can add an Article in a Basket. Synchronized.
	 * @param article
	 */
	@Transient
	public synchronized void addArticle(Article article) {
		this.modifyArticleQuantity(article, Integer.valueOf( +1 ));
	}
	
	
	@Transient
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

		// Removing more article than there are in basket?
		if (initialQuantity.compareTo(Math.abs(quantityDifference)) < 0) {
			// FIXME: decide wether or not to throw an exception in this case
			throw new IllegalArgumentException("Can't set remove <" + Math.abs(quantityDifference) + "> articles from basket, when there are only <" + initialQuantity + "> in given basket.");
			// alternative : set quantityDifference to -initialQuantity
		}
		
		// Computing final quantity
		Integer finalQuantity = initialQuantity + quantityDifference;
		
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
			
			// Change quantity
			this.getOrderLines().put(article, finalQuantity);
		}
		
	}
	
	
	
	/**
	 * One can remove an Article from a Basket
	 */
	@Transient
	public void clearArticle(Article article) {
		if (article == null) {
			throw new IllegalArgumentException("Can't remove articles <null> from basket!");
		}
		this.getOrderLines().remove(article);
	}
	
	
	
	
	/** JPA empty constructor */
	public Basket() {}
	
}
