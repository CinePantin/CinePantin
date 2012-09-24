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
	
	
	
	/**
	 * Book-aware short description
	 */
	@Override
	public String getShortDescription() {
		return this.getTitle() + " - " + this.getAuthor();
	}
	
	
	
	/** JPA empty constructor */
	public Book() {}
	
	
	
	/**
	 * Regular (business) Book constructor.
	 * @param title		Book title		:-)
	 * @param author	Book author		;-D
	 * @param vatRate	Book VAT rate	X-]]
	 */
	public Book(String title, String author, VatRate vatRate) {
		this.author = author;
		this.title = title;
		this.setVatRate(vatRate);
	}
}
