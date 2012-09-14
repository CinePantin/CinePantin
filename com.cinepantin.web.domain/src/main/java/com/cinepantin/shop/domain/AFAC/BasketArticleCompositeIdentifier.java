package com.cinepantin.shop.domain.AFAC;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cinepantin.shop.domain.Article;
import com.cinepantin.shop.domain.Basket;
import com.cinepantin.shop.domain.UserBasket;

/**
 * BasketArticleCompositeIdentifier is a clustered (basketId + articleId) identifier
 * 		for (JPA @embedded) use in BasketArticle (a better "OrderLine" class)
 * 
 */
@Embeddable
public class BasketArticleCompositeIdentifier
		implements Serializable // if net: org.hibernate.MappingException: composite-id class must implement Serializable: com.cinepantin.shop.domain.BasketArticleCompositeIdentifier
{

	private static final long serialVersionUID = -2995767439032510028L;

	

	@ManyToOne(
			targetEntity=com.cinepantin.shop.domain.UserBasket.class,
			optional=false
			)
	@JoinColumn( name="basketId", referencedColumnName="basketId")
	public Basket basket;
	

	@ManyToOne(optional=false)
	@JoinColumn(name="articleId", referencedColumnName="articleId")
	public Article article;
}
