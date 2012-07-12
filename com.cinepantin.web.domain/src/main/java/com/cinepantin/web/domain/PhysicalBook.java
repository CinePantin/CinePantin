package com.cinepantin.web.domain;

public interface PhysicalBook extends PhysicalArticle {
	
	/**
	 * PhysicalBook(s) must implement a "title" field with accessors.
	 */
	public String getTitle();
	
	/**
	 * PhysicalBook(s) must implement an "author" field with accessors.
	 */
	public String getAuthor();
}
