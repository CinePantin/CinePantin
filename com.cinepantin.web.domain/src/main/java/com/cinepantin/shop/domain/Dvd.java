package com.cinepantin.shop.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="Dvd") //Ignored by Hibernate...
public class Dvd extends PhysicalArticle {
	
	public String director;
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) { // TODO: shouldn't this be made protected ?
		this.director = director;
	}
	
	public String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) { // TODO: shouldn't this be made protected ?
		this.title = title;
	}
	
	public Dvd() {
		// public, empty, JPA...
	}
	
	public Dvd(String title, String director, VatRate vatRate) {
		this.title = title;
		this.director = director;
		this.setVatRate(vatRate);
	}
	
	@Override
	public String getShortDescription() {
		return "(DVD) " + this.title + " - réalisateur : " + this.director;
	}
	
}
