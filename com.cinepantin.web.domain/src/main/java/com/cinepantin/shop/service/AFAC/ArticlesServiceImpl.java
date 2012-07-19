package com.cinepantin.shop.service.AFAC;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.cinepantin.web.domain.Article;

@Transactional
public class ArticlesServiceImpl { // implements ArticleService<Article> {

	/**
	 * 	Spring-injected EntityManager
	 */
	private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	public Article find(int idArticle) {
		return em.find(Article.class, idArticle);
	}

	public List<Article> findAll() {
		TypedQuery<Article> tq = em.createQuery("select a from Article a", Article.class );
		return tq.getResultList();
	}

	public void save(Article article) {
		if (article.getIdArticle() == 0) { // New article
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

}
