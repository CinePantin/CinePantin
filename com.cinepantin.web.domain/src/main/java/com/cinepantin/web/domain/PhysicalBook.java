package com.cinepantin.web.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="idArticle")
public class PhysicalBook extends PhysicalArticle {
	
	private String title;
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	private String author;
	public String getAuthor() {
		return this.author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/*
	 * 	Override methods
	 */
	@Override
	public String getDescription() {
		return this.getTitle() + " - par " + this.getAuthor();
	}
	
	
	public PhysicalBook() {
		// empty constructor, as per JPA requirements
	}
	
	public PhysicalBook(String title, String author) {
		super();
		this.author = author;
		this.title = title;
	}
}
