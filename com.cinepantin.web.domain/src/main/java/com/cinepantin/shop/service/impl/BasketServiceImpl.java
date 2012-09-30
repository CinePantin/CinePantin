package com.cinepantin.shop.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.cinepantin.shop.domain.Basket;
import com.cinepantin.shop.domain.User;
import com.cinepantin.shop.domain.UserBasket;
import com.cinepantin.shop.domain.Visitor;
import com.cinepantin.shop.service.BasketService;

@Transactional
public class BasketServiceImpl implements BasketService {
	
	/**
	 * 	Spring-injected EntityManager
	 */
	private EntityManager em;
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    
    
	/** Quite useless: just invokes Visitor.getBasket() */
	public Basket find(Visitor visitor) {
		return visitor.getBasket();
	}
	
	
	
	public UserBasket find(int id) {
		return em.find(UserBasket.class, id);
	}
	
	/** Quite useless: just invokes User.getBasket() */ 
	public UserBasket find(User user) {
		return user.getBasket();
	}

	public void save(UserBasket userBasket) {
		if ((userBasket.getBasketId() == null) || (userBasket.getBasketId() == 0)) { // New basket
			System.out.println("About to em.persist()");
			em.persist(userBasket);
			System.out.println("Done");
		} else {
			System.out.println("About to em.merge()");
			em.merge(userBasket);
			System.out.println("Done");
		}
	}

	public void remove(int id) {
		UserBasket userBasket = em.find(UserBasket.class, id);
		if (userBasket != null) {
			em.remove(userBasket);
		}
	}

}
