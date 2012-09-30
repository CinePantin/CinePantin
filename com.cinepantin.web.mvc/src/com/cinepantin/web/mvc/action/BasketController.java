package com.cinepantin.web.mvc.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.cinepantin.shop.domain.Article;
import com.cinepantin.shop.domain.Basket;
import com.cinepantin.shop.domain.Book;
import com.cinepantin.shop.domain.UserBasket;
import com.cinepantin.shop.domain.Visitor;
import com.cinepantin.shop.service.ArticleService;
import com.cinepantin.shop.service.BasketService;
import com.cinepantin.shop.service.UserService;
import com.cinepantin.web.mvc.action.support.Attributes;
import com.cinepantin.web.mvc.action.support.SessionSupport;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;



public class BasketController 
		extends ActionSupport // TODO: investigate benefits of using org.springframework.web.struts.ActionSupport instead
		implements 
			ServletRequestAware
			,
			Preparable
{

	private static final long serialVersionUID = 1971075851225109753L;
	
	/** Spring-injected **/
	@Autowired
	BasketService basketService;

	/** Spring-injected **/
	@Autowired
	UserService userService;

	/** Spring-injected **/
	@Autowired
	ArticleService<Book> articleService;
	
	SessionSupport ss;
	
	public Basket basket;
	public Basket getBasket() {
		return this.basket;
	}
	
	private Visitor visitor;

	public Integer articleId;
	public Integer quantity;
	public Article removedArticle;
	
	private HttpSession session;
	/** ServletRequestAware implementation **/
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.session = request.getSession();
		// FIXME : use ActionContext.getContext().getSession().put(key, value) instead of the HttpSession (as long as attributes are concerned) 
	}
	
	

	public String view() {
		// all the heavy work has already been done in prepare(). Thank you, prepare()!
		return SUCCESS;
	}
	
	public String addBook() {
		return this.addPhysicalArticle();
	}
	
	public String addDvd() {
		return this.addPhysicalArticle();
	}
	
	private String addPhysicalArticle() {
		Map<String, Object> params = ActionContext.getContext().getParameters();
		this.articleId = Integer.parseInt( params.get("articleId").toString() );
		this.quantity = Integer.parseInt( params.get("quantity").toString() );
//		System.out.println("addBook was called...");
//		System.out.println("articleId = " + articleId);
//		System.out.println("quantity = " + quantity);
		
		basket.modifyArticleQuantity(
				articleService.find(articleId),
				quantity
		);
		session.setAttribute(Attributes.Session.visitor, this.visitor); // Is this necessary ?
		if (basket instanceof UserBasket) {
			System.out.println();
			System.out.println("	========================================================");
			System.out.println("	addPhysicalArticle() is about to save updated UserBasket");
			System.out.println("	========================================================");
			System.out.println();
			basketService.save((UserBasket) basket); // FIXME: catch exception
			System.out.println();
			System.out.println("	================================");
			System.out.println("	 save (UserBasket) has returned ");
			System.out.println("	================================");
			System.out.println();
		}
		
		/* DEBUG * / 
		System.out.println("Basket contents:");
		for (Entry<Article, Integer> ol : basket.getOrderLines().entrySet()) {
			System.out.println( 
					"\t"
					+ ((Article)ol.getKey())
							.getShortDescription()
					+ "\t"
					+ ol.getValue()
					);
		}
		/**/
		
		return SUCCESS;
	}
	
	public String removeBookRef() {
		return removePhysicalArticleRef();
	}
	
	public String removeDvdRef() {
		return removePhysicalArticleRef();
	}
	
	public String removePhysicalArticleRef() {
		Map<String, Object> params = ActionContext.getContext().getParameters();
		this.articleId = Integer.parseInt( params.get("articleId").toString() );
		this.removedArticle = this.articleService.find(this.articleId);
		basket.clearArticle(articleService.find(this.articleId));

		if (basket instanceof UserBasket) {
			System.out.println();
			System.out.println("	==============================================================");
			System.out.println("	removePhysicalArticleRef() is about to save updated UserBasket");
			System.out.println("	==============================================================");
			System.out.println();
			basketService.save((UserBasket) basket); // FIXME: catch exception
			System.out.println();
			System.out.println("	================================");
			System.out.println("	 save (UserBasket) has returned ");
			System.out.println("	================================");
			System.out.println();
		}
		return SUCCESS;
	}
	
	
	/** clears basket from all contents **/
	public String clearAll() {
		this.basket.clearBasket();

		if (basket instanceof UserBasket) {
			System.out.println();
			System.out.println("	==============================================");
			System.out.println("	clearAll() is about to save cleared UserBasket");
			System.out.println("	==============================================");
			System.out.println();
			basketService.save((UserBasket) basket); // FIXME: catch exception
			System.out.println();
			System.out.println("	================================");
			System.out.println("	 save (UserBasket) has returned ");
			System.out.println("	================================");
			System.out.println();
		}
		return SUCCESS;
	}



	@Override
	public void prepare() throws Exception 
	{
		this.visitor = (Visitor) session.getAttribute(Attributes.Session.visitor);
		if (this.visitor == null) 
		{
			this.visitor = new Visitor();
		}
		this.basket = this.visitor.getBasket();
		
		// TODO: remove this TEST
		session.setAttribute("foo", "bar");
	}
}
