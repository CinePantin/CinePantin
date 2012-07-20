package com.cinepantin.shop.domain;

import javax.persistence.Column;
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
public class Article {

	/**
	 * Articles MUST implement an "idArticle" field with accessors.
	 * That field MUST be anotated "@Id" (javax.persistence API) 
	 */
	private int idArticle;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdArticle()  {
		return this.idArticle;
	}
	protected void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}
	
	private TxTva txTva;
	public TxTva getTxTva() {
		return txTva;
	}
	protected void setTxTva(TxTva txTva) {
		this.txTva = txTva;
	}

	private String shortDescription = "Les classes filles doivent surcharger cette méthode !";
	@Column(name="ShortDescription", insertable=true, updatable=true)
	public String getDescription() {
		return this.shortDescription;
	}
	void setDescription(String description) {
		this.shortDescription = description;
	}

	public Article() {
		// JPA empty constructor
	}
}
