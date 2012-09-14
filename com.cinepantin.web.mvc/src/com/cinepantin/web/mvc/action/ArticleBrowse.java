package com.cinepantin.web.mvc.action;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.opensymphony.xwork2.ActionSupport;

import com.cinepantin.shop.domain.Article;
import com.cinepantin.shop.domain.Book;
import com.cinepantin.shop.service.ArticleService;

public class ArticleBrowse 
		extends ActionSupport
		// implements ModelDriven<Article> 					
{
	private static final long serialVersionUID = -7561165783871060968L;
	
	// TODO: lire http://struts.apache.org/2.0.12/docs/struts-2-spring-2-jpa-ajax.html
	
	private ArticleService<? extends Article> articleService;
	private List<? extends Article> articles;
	public List<? extends Article> getArticles() {
		return this.articles;
	}
	
	/** STRUTS model, Spring-injected ** /
	// @Autowired
	private Article model;
	public void setModel(Article model) {
		this.model = model;
	}
	public Article getModel() {
		return this.model;
	}
	*/
	
	/** Spring-injected EntityManager **/
	@PersistenceContext(unitName="CinePantinPersistenceUnit")
	private EntityManager em;
    public EntityManager getEm() {
		return em;
	}
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
	
	/**
	 * STRUTS action
	 * @return
	 */
	public String list() {
		TypedQuery<Book> tq = em.createQuery("select b from Book b", Book.class);
    	List<Book> l = tq.getResultList();
    	
    	if (l.size() > 0 ) {
	    	for (Book b : l) {
	    		System.out.println(b.getShortDescription());
	    	}
    	} else {
    		System.out.println("vide...");
    	}
		//TODO: should check (this.user == null) here and return ERROR/SUCCESS
		// return Action.SUCCESS;
    	
    	
    	return "success-tiles";
	}
	
	public String list2() {
		this.articles = articleService.findAll();
		return "success-tiles";
	}
	
	
	public ArticleBrowse(ArticleService<? extends Article> articleService) {
		this.articleService = articleService;
	}
	
}
