package com.cinepantin.shop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

/**
 * Basket (plain Domain class, NOT a persistent entity)
 *
 */
@MappedSuperclass
public class Basket {

	private List<OrderLine> orderLineList;
	@OneToMany(
			targetEntity=com.cinepantin.shop.domain.OrderLine.class, // not necessary : "Defaults to the parameterized type of the collection when defined using generics." (says javadoc) 
			// cascade=ALL, // Entity baskets can't be deleted anyway...
            mappedBy="basket")
	public List<OrderLine> getOrderLineList() {
		return orderLineList;
	}
	protected void setOrderLineList(List<OrderLine> orderLineList) {
		this.orderLineList = orderLineList;
	}

	public Basket() {
		this.orderLineList = new ArrayList<OrderLine>();
	}

}
