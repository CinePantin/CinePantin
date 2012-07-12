package com.cinepantin.web.domain;

public interface Article {

	/**
	 * Articles MUST implement an "idArticle" field with accessors.
	 * That field MUST be anotated "@Id" (javax.persistence API) 
	 */
	public int getIdArticle();

	public String getDescription();
	
}
