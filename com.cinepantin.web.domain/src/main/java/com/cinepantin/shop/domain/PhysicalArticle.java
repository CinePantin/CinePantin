package com.cinepantin.shop.domain;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
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
