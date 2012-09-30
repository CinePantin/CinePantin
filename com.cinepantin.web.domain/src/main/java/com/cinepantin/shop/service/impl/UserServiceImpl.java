package com.cinepantin.shop.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.cinepantin.shop.domain.User;
import com.cinepantin.shop.service.UserService;

//@NamedQueries({
//	@NamedQuery(
//			name="canAuthenticateWithCredentials", 
//			query="SELECT u FROM User u WHERE u.login = :login AND u.password = :password;",
//			lockMode=LockModeType.NONE
//			)
//})
public class UserServiceImpl implements UserService {
	
	/**
	 * 	Spring-injected EntityManager
	 */
	private EntityManager em;
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    

	public User find(int id) {
		return em.find(User.class, id);
	}
	
	
	
	/**
	 * Authentication method
	 * @param login
	 * @param password
	 * @return userId if authentication was successful, or null if authentication failed
	 */
	public Integer getUserIdFromCredentials(String login, String password) {
		Integer userId;
		TypedQuery<Integer> tq = em.createQuery(
				"SELECT u.userId FROM User u " +
					"WHERE u.login = :prmLogin " +
					"AND u.password = :prmPassword"
				,Integer.class
		);
		tq.setParameter("prmLogin", login);
		tq.setParameter("prmPassword", password);
		try {
			userId = tq.getSingleResult(); 
		} catch (NoResultException nre) {
			userId = null;
		}
		
		return userId;
	}

	
	
	public void save(User user) {
		if (user.getUserId() == 0) { // New basket
			em.persist(user);
		} else {
			em.merge(user);
		}
	}

}
