package com.cinepantin.web.mvc.action.support;

import java.util.Map;
import java.util.Map.Entry;

import com.cinepantin.shop.domain.Basket;
import com.cinepantin.shop.domain.Visitor;
import com.opensymphony.xwork2.ActionContext;

public class SessionSupport {

	ActionContext context;
	Map<String, Object> sessionMap;
	
	Visitor visitor;
	
	public SessionSupport(ActionContext actionContext) {
		
		// DEBUG
		Map<String, Object> map = actionContext.getContextMap();
		for (Entry<String, Object> e1 : map.entrySet()) {
			System.out.println(
					e1.getKey() 
					+ "\t" 
					+ e1.getValue()
			);
			if (e1.getValue() instanceof Map) {
				Map<?,?> m2 = (Map<?,?>) e1.getValue();
				for (Entry<?,?> e2 : m2.entrySet()) {
					System.out.println(
							"\t"
							+ e2.getKey() 
							+ "\t" 
							+ e2.getValue()
					);
				}
			}
		}
		
		
//		this.context = actionContext;
//		this.sessionMap = context.getSession();
	}
	
	
	public Visitor getVisitor() {
		Visitor v = (Visitor) this.sessionMap.get(Attributes.Session.visitor);
		if ( v == null ) {
			v = new Visitor();
			this.sessionMap.put(Attributes.Session.visitor, v);
		}
		return v;
	}
	// TODO: setVisitor(), and possibly setUser and getUser...
	
	
	
	public Basket getBasket() {
		Basket b = (Basket) this.sessionMap.get(Attributes.Session.basket);
		if ( b == null ) {
			Visitor v = this.getVisitor();
			b = v.getBasket();
			this.sessionMap.put(Attributes.Session.basket, b);
		}
		return b;
	}
	
	public void setBasket(Basket basket) {
		this.sessionMap.put(Attributes.Session.basket, basket);
	}
}
