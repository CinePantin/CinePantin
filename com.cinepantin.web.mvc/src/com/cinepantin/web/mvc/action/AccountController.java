package com.cinepantin.web.mvc.action;

import static com.opensymphony.xwork2.Action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.cinepantin.shop.domain.User;
import com.cinepantin.shop.domain.UserBasket;
import com.cinepantin.shop.domain.Visitor;
import com.cinepantin.shop.service.BasketService;
import com.cinepantin.shop.service.UserService;
import com.cinepantin.web.mvc.action.support.Attributes;
import com.opensymphony.xwork2.ModelDriven;

public class AccountController 
		implements 
			ModelDriven<AccountModel> 
			,
			ServletRequestAware
{

	AccountModel accountModel;
	
	/** Spring-injected service **/
	@Autowired
	UserService userService;
	
	/** Spring-injected service **/
	@Autowired
	BasketService basketService;
	
	@Override
	public AccountModel getModel() {
		if (this.accountModel == null) {
			this.accountModel = new AccountModel(); 
		}
		return this.accountModel;
	}
	public void setModel(AccountModel accountModel) {
		this.accountModel = accountModel;
	}
	
	private HttpServletRequest request;
	private HttpSession session;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}
	
	
	/**
	 * Struts action: "login"
	 */
	public String login() {
		// TODO: Implement login action (currently dummy code).
//		if ( ("toto".equals(accountModel.getLogin())) && ("toto".equals(accountModel.getPassword())) ) {
//			session.setAttribute("userIsAuthenticated", true);
//			session.setAttribute("userLogin", accountModel.getLogin());
//			session.setAttribute("userName", accountModel.getLogin());
//			return SUCCESS;
//		} else {
//			session.setAttribute("userIsAuthenticated", false);
//			session.setAttribute("userLogin", null);
//			request.getSession().setAttribute("login", null);
//			return ERROR;
//		}
		
		Integer userId = userService.getUserIdFromCredentials(
				accountModel.getLogin(), 
				accountModel.getPassword()
				);
		if (userId != null) {
			User user = userService.find(userId);
			UserBasket userBasket = user.getBasket();
			session.setAttribute(Attributes.Session.visitor, user);
			session.setAttribute("userIsAuthenticated", true);
			session.setAttribute("userLogin", user.getLogin());
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("basketId", userBasket.getBasketId());
			session.setAttribute("DZC_basketSize", userBasket.getOrderLines().size());
			return SUCCESS;
		} else {
			session.setAttribute(Attributes.Session.visitor, new Visitor());
			session.setAttribute("userIsAuthenticated", false);
			session.setAttribute("userLogin", null);
			session.setAttribute("userId", null);
			session.setAttribute("basketId", null);
			session.setAttribute("DZC_basketSize", null);
			return ERROR;
		}
	}
	
	
	/**
	 * Struts action: "logout"
	 */
	public String logout() {
		// TODO: Implement logout action (currently dummy code).
			session.setAttribute(Attributes.Session.visitor, new Visitor());
			session.setAttribute("userIsAuthenticated", false);
			session.setAttribute("userLogin", null);
			session.setAttribute("userId", null);
			session.setAttribute("basketId", null);
			session.setAttribute("DZC_basketSize", null);
			return SUCCESS;
	}

}
