package com.cinepantin.shop.service.impl;

import com.cinepantin.shop.domain.Book;
import com.cinepantin.shop.service.BookService;

public class BookServiceImpl
	extends ArticleServiceImpl<Book>
	implements BookService
{

	public BookServiceImpl() {
		super(new Book());
	}


}
