package com.cinepantin.web.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="Books")
@DiscriminatorValue("Books") // Si non défini, le nom de l'entité sera utilisé
@PrimaryKeyJoinColumn(name="idArticle")
public class PhysicalBookImpl extends PhysicalArticleImpl implements PhysicalBook {

	/*
	 * 	PhysicalBook interface implementation
	 */
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
	
	
	public PhysicalBookImpl() {
		// empty constructor, as per JPA requirements
	}
	public PhysicalBookImpl(String title, String author) {
		super();
		this.author = author;
		this.title = title;
	}
}
