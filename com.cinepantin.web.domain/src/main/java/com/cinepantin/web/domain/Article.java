package com.cinepantin.web.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(
					discriminatorType=DiscriminatorType.STRING,
					name="instanceOf",
					length=31
)
public abstract class Article {

	/**
	 * Articles MUST implement an "idArticle" field with accessors.
	 * That field MUST be anotated "@Id" (javax.persistence API) 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idArticle;
	public int getIdArticle()  {
		return this.idArticle;
	}

	// @Column(name="ShortDescription", insertable=true, updatable=true, table="Articles")
	public abstract String getDescription();
	// public void setDescription(String description);
}
