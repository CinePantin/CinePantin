package com.cinepantin.web.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import com.cinepantin.web.domain.test.TxTva;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(
					discriminatorType=DiscriminatorType.STRING,
					name="instanceOf",
					length=31
)
@PrimaryKeyJoinColumn(name="idArticle")
public abstract class PhysicalArticle extends Article {
	
	public PhysicalArticle() {
	}
	private int stockQuantity;
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

}
