package com.cinepantin.shop.domain;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Entity
@DiscriminatorValue(value="Livre") //Ignored by Hibernate...
public class Book extends PhysicalArticle {
	
	private String title;
	@Basic(fetch=FetchType.LAZY)
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) { // TODO: shouldn't this be made protected ?
		this.title = title;
	}

	private String author;
	@Basic(fetch=FetchType.LAZY)
	public String getAuthor() {
		return this.author;
	}
	public void setAuthor(String author) { // TODO: shouldn't this be made protected ?
		this.author = author;
	}
	
	/*
	 * 	Override methods
	 */
	@Override
	public String getShortDescription() {
		return "(Livre) " + this.getTitle() + " - auteur : " + this.getAuthor();
	}
	
	
	public Book() {
		// empty constructor, as per JPA requirements
	}
	
	public Book(String title, String author, VatRate vatRate) {
		this.author = author;
		this.title = title;
		this.setVatRate(vatRate);
	}
}
