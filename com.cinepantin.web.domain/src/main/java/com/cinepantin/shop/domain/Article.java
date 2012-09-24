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


/**
 * Article (Domain entity). Root class for all kind of articles we sell.
 *
 */
@Entity
@Table(name="Article")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING, length=20) // Ignored by Hibernate when JOINED inheritance is used... 
public class Article {
	
	
	
	private Integer articleId;
	/**
	 * JPA technical PK 
	 * 
	 */
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
	private String shortDescription = "Child classes MUST override this method! " +
										"Seing this anywhere is proof one didn't. " +
										"A developper will be sacrificed to calm the gods down.";
	@Column(name="shortDescription")
	public String getShortDescription() {
		return this.shortDescription;
	}
	public void setShortDescription(String description) {
		this.shortDescription = description;
	}
	
	
	
	/**	JPA empty constructor */
	public  Article() {
		// this.vatRate = new VatRate();
	}
	
	
	
	
	public Article(VatRate vatRate) {
		this.vatRate = vatRate;
	}

}
