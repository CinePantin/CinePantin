package com.cinepantin.shop.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="Article")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING, length=20) // Ignored by Hibernate when JOINED inheritance is used... 
public class Article {

	private Integer articleId;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getArticleId()  {
		return this.articleId;
	}
	protected void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	
	private VatRate vatRate;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vatId")
	public VatRate getVatRate() {
		return this.vatRate;
	}
	protected void setVatRate(VatRate vatRate) {
		this.vatRate = vatRate;
	}

	/**
	 * Short description, to be used in "Article" context (e.g.: shopping basket).
	 * 
	 * Articles child classes MUST override this method.
	 *  
	 */
	private String shortDescription = "Les classes filles doivent surcharger cette méthode !";
	@Column(name="shortDescription")
	public String getShortDescription() {
		return this.shortDescription;
	}
	void setShortDescription(String description) {
		this.shortDescription = description;
	}

	protected Article() {
		// JPA empty constructor
		this.vatRate = new VatRate();
	}

}
