package com.cinepantin.shop.domain;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;


@Entity
@DiscriminatorValue(value="PhysicalArticle")
public class PhysicalArticle extends Article {
	
	public PhysicalArticle() {
	}
	private int stockQuantity;
	@Basic(fetch=FetchType.LAZY)
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

}
