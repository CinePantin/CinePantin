package com.cinepantin.web.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author STAGIAIRE
 *
 */

@Entity
@Table(name="Articles")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(
					discriminatorType=DiscriminatorType.STRING,
					name="join_table",
					length=31
)
public class ArticleImpl implements Article {

	/*
	 *		Article interface implementation 
	 */
	@Id
	@Column(name="idArticle")
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int idArticle;
	public int getIdArticle() {
		return this.idArticle;
	}
	
	@Column(name="toto")
	private String toto = "toto";
	
	public String getDescription() {
		// TODO Mock code, implementation pending
		return "Description de l'article";
	}
	
	/**
	 * 
	 */
	public ArticleImpl() {
		
	}

}
