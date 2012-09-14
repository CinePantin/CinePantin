package com.cinepantin.shop.domain.DZC;

public class Money<M> {
// public class Money<M extends Money<M>> {
	
	private int amount;
	public int getAmount() {
		return this.amount;
	}
	
	public Money(int amount) {
		this.amount = amount;
	}
	
	public int add(int amount) {
		this.amount += amount;
		return this.amount;
	}
	
	public int add(Money<M> monney) {
		this.amount += monney.getAmount();
		return this.amount;
	}
}
