package com.cinepantin.web.domain.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;


import com.cinepantin.web.domain.PhysicalBook;
import com.cinepantin.web.domain.PhysicalBookImpl;


public class Tester {
	
	
	@PersistenceContext(unitName="TestArticlePersistenceUnit")
	private EntityManager em;
    public EntityManager getEm() {
		return em;
	}
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Transactional
    void test() {

    	PhysicalBook book = new PhysicalBookImpl(1, "Le nom de la Rose", "Zorro");
    	
    	em.persist(book);
        System.out.println(book.getDescription());
    }
}
