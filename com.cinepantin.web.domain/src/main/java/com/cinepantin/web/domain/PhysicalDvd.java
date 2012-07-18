package com.cinepantin.web.domain;

import javax.persistence.Entity;

@Entity
public class PhysicalDvd extends PhysicalArticle {
	
	public String director;
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	
	public String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public PhysicalDvd() {
		// public, empty, JPA...
	}
	
	@Override
	public String getDescription() {
		return "DVD : " + this.title + " - " + this.director;
	}
	
}
