package com.cinepantin.web.domain.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cinepantin.web.domain.PhysicalBook;
import com.cinepantin.web.domain.PhysicalBookImpl;

public class Test {

	
	@PersistenceContext(unitName="TestArticlePersistenceUnit")
	private EntityManager em;
	

    public static void main( String[] args )
    {
    	Test t = new Test();
    	t.test();
 
    }
    
    private void test() {

    	ApplicationContext context = 
    		new ClassPathXmlApplicationContext("com/cinepantin/web/domain/test/Spring-ApplicationContext.xml");
 
    	PhysicalBook book = new PhysicalBookImpl(1, "Le nom de la Rose", "");

    	em.persist(book);
        System.out.println(book.getDescription());
    }
	
	
}
