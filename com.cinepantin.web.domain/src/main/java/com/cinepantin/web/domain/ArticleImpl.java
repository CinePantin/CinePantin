package com.cinepantin.web.domain;

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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idArticle;
	public int getIdArticle() {
		return this.idArticle;
	}
	public void setArticleId(int idArticle) {
		this.idArticle = idArticle;
	}
	
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
