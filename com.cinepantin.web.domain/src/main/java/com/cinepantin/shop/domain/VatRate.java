package com.cinepantin.shop.domain;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VatRate {

	private int vatId;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getVatId() {
		return this.vatId;
	}
	protected void setVatId(int idTva) {
		this.vatId = idTva;
	}
	
	
	
	private BigDecimal multiplier;
	@Basic(fetch=FetchType.LAZY)
	public BigDecimal getMultiplier() {
		return multiplier;
	}
	protected void setMultiplier(BigDecimal multiplier) {
		this.multiplier = multiplier;
	}
	
	
	
	public VatRate(BigDecimal multiplier) {
		this.multiplier = multiplier;
	}
	
	public VatRate() {
		// JPA still requires a no-arg, empty constructor
	}

}
