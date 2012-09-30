package com.cinepantin.web.mvc.action;

import java.util.List;

import com.cinepantin.shop.domain.Article;
import com.cinepantin.shop.service.ArticleService;
import com.opensymphony.xwork2.ActionSupport;

public class HomeController 
			extends ActionSupport		
{
	private static final long serialVersionUID = -2459134993319149709L;
	
	/** constructor will allow Spring to inject this service **/
	private ArticleService<? extends Article> articleService;
	
	private List<? extends Article> articles;
	public List<? extends Article> getArticles() {
		return this.articles;
	}
	
	public String index() {
		this.articles = articleService.findAll();
		return SUCCESS;
	}
	
	public HomeController(ArticleService<? extends Article> articleService) {
		this.articleService = articleService;
	}
	
}
