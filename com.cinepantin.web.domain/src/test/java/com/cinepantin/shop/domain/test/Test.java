package com.cinepantin.shop.domain.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main( String[] args )
    {

		ApplicationContext context = 
		    	new ClassPathXmlApplicationContext("com/cinepantin/shop/domain/test/Spring-ApplicationContext.xml");
 
    	
    	Tester t = (Tester) context.getBean("testeur");
    	t.test();
 
  
    	
    }
	
	
}
