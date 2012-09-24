package com.cinepantin.shop.domain;



import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;



/**
 * A physical article provides stock functionality to its child classes.
 *
 */
@MappedSuperclass
public class PhysicalArticle extends Article {
	
	
	
	private int stockQuantity;
	@Basic(fetch=FetchType.LAZY)
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	
	
	
	/** JPA empty constructor */
	public PhysicalArticle() {}
	
	
	
}
