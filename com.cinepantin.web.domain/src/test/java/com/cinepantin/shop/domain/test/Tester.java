package com.cinepantin.shop.domain.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;


import com.cinepantin.shop.domain.Article;
import com.cinepantin.shop.domain.PhysicalBook;
import com.cinepantin.shop.domain.PhysicalDvd;
import com.cinepantin.shop.service.ArticleService;


public class Tester {
	
	
	@PersistenceContext(unitName="TestArticlePersistenceUnit")
	private EntityManager em;
    public EntityManager getEm() {
		return em;
	}
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
	/** Cible pour auto-injection Spring **/
	private ArticleService<Article> articleService;
	@Autowired
	public void setArticleService(ArticleService<Article> articleService) {
		this.articleService = articleService;
	}

	@Transactional()
    public void test() {

		ApplicationContext context = 
		    	new ClassPathXmlApplicationContext("com/cinepantin/web/domain/test/Spring-ApplicationContext.xml");
		
    	
    	PhysicalDvd dvd = new PhysicalDvd();
    	dvd.setDirector("David Lynch");
    	dvd.setTitle("Lost Highway");
    	em.persist(dvd);
		
    	 PhysicalBook book = new PhysicalBook("Le nom de la Rose", "Umberto Ecco");
		/*PhysicalBookImpl book = (PhysicalBookImpl) context.getBean(PhysicalBookImpl.class) ;
    	book.setAuthor("Marx");
    	book.setTitle("Le capital");
    	*/
    	

        System.out.println(book.getIdArticle());
    	em.persist(book);
    	// em.flush();
        System.out.println(book.getIdArticle());
    	
    	
//    	TypedQuery<PhysicalBookImpl> tq = em.createQuery("select b from PhysicalBookImpl b", PhysicalBookImpl.class);
//    	List<PhysicalBookImpl> l = tq.getResultList();
//    	if (l.size() > 0 ) {
//	    	for (PhysicalBookImpl b : l) {
//	    		System.out.println(b.getDescription());
//	    	}
//    	} else {
//    		System.out.println("vide...");
//    	}
        
    	TypedQuery<Article> tq = em.createQuery("select a from Article a", Article.class);
    	List<Article> l = tq.getResultList();
        dumpArticleList(l);
    	
    	
    	System.out.println("====== And now for some Spring magic (hopefully...) ========");
    	List<Article> l2 = articleService.findAll();
    	dumpArticleList(l2);
    	
    }
	
	private void dumpArticleList(List<Article> l) {
		if (l.size() > 0 ) {
	    	for (Article a : l) {
	    		if (a instanceof PhysicalBook) {
	    			System.out.print(
	    				"Ceci est un livre (l'auteur est " + ((PhysicalBook) a).getAuthor() + ") -- "
	    			);
	    		}
	    		System.out.println(a.getDescription());
	    	}
    	} else {
    		System.out.println("vide...");
    	}
	}
	
	
}
