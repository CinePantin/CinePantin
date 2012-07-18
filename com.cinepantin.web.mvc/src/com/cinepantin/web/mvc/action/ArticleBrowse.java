package com.cinepantin.web.mvc.action;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import com.cinepantin.web.domain.Article;
import com.cinepantin.web.domain.PhysicalBook;

public class ArticleBrowse 
		extends ActionSupport
		implements ModelDriven<Article> 					
{
	private static final long serialVersionUID = -7561165783871060968L;
	
	/** STRUTS model, Spring-injected **/
	@Autowired
	private Article model;
	public void setModel(Article model) {
		this.model = model;
	}
	@Override
	public Article getModel() {
		return this.model;
	}
	
	/** Spring-injected EntityManager **/
	@PersistenceContext(unitName="TestArticlePersistenceUnit")
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
	public String browse() {
		TypedQuery<PhysicalBook> tq = em.createQuery("select b from PhysicalBookImpl b", PhysicalBook.class);
    	List<PhysicalBook> l = tq.getResultList();
    	
    	if (l.size() > 0 ) {
	    	for (PhysicalBook b : l) {
	    		System.out.println(b.getDescription());
	    	}
    	} else {
    		System.out.println("vide...");
    	}
		//TODO: should check (this.user == null) here and return ERROR/SUCCESS
		return Action.SUCCESS;
	}
	
}
