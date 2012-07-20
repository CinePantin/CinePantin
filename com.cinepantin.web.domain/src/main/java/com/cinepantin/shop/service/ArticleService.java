package com.cinepantin.shop.service;

import java.util.List;

import com.cinepantin.shop.domain.Article;

public interface ArticleService<A extends Article> {

	public A find(int id);
	
	public List<A> findAll();
	
	public void save (A article);
	
	public void remove(int idArticle);
	
}
