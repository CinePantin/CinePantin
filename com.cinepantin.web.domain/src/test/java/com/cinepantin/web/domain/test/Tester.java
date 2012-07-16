package com.cinepantin.web.domain.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;


import com.cinepantin.web.domain.PhysicalBook;
import com.cinepantin.web.domain.PhysicalBookImpl;
import com.cinepantin.web.domain.PhysicalDvd;
import com.cinepantin.web.domain.PhysicalDvdImpl;


public class Tester {
	
	
	@PersistenceContext(unitName="TestArticlePersistenceUnit")
	private EntityManager em;
    public EntityManager getEm() {
		return em;
	}
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Transactional()
    public void test() {

		ApplicationContext context = 
		    	new ClassPathXmlApplicationContext("com/cinepantin/web/domain/test/Spring-ApplicationContext.xml");
		

    	
    	PhysicalDvdImpl dvd = new PhysicalDvdImpl();
    	dvd.setDirector("David Lynch");
    	em.persist(dvd);
		
    	 PhysicalBook book = new PhysicalBookImpl("Le nom de la Rose", "Zorro");
		/*PhysicalBookImpl book = (PhysicalBookImpl) context.getBean(PhysicalBookImpl.class) ;
    	book.setAuthor("Marx");
    	book.setTitle("Le capital");
    	*/
    	
    	 
    	em.persist(book);
    	em.flush();
    	TypedQuery<PhysicalBookImpl> tq = em.createQuery("select b from PhysicalBookImpl b", PhysicalBookImpl.class);
    	List<PhysicalBookImpl> l = tq.getResultList();
    	
    	
    	if (l.size() > 0 ) {
	    	for (PhysicalBookImpl b : l) {
	    		System.out.println(b.getDescription());
	    	}
    	} else {
    		System.out.println("vide...");
    	}
        System.out.println(book.getIdArticle());
    }
	
	
}
