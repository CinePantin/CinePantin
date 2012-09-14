package com.cinepantin.shop.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.cinepantin.shop.domain.Article;
import com.cinepantin.shop.service.ArticleService;

@Transactional
public class ArticleServiceImpl<A extends Article> 
  implements ArticleService<A> 
{

	private Class<A> articleClass;
	//private A type;
	
	/**
	 * 	Spring-injected EntityManager
	 */
	private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	public A find(int idArticle) {
		return em.find(this.articleClass, idArticle);
	}

	public List<A> findAll() {
		// TypedQuery<A> tq = (TypedQuery<A>) em.createQuery("select a from Article a", articleClass );
		TypedQuery<A> tq = (TypedQuery<A>) em.createQuery(
				"select a from " + articleClass.getName() + " a", 
				articleClass 
		);
		return tq.getResultList();
	}

	public void save(A article) {
		if (article.getArticleId() == 0) { // New article
			em.persist(article);
		} else {
			em.merge(article);
		}
	}

	public void remove(int idArticle) {
		Article article = em.find(Article.class, idArticle);
		if (article != null) {
			em.remove(article);
		}
		
	}
	
//	public ArticleServiceImpl(Class<A> articleClass) {
//		this.articleClass = articleClass;
//	}
	
	@SuppressWarnings("unchecked")
	public ArticleServiceImpl(A type) {
		// this.type = type;
		this.articleClass = (Class<A>) type.getClass();
	}

}
