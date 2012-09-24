package com.cinepantin.shop.domain.test;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;


import com.cinepantin.shop.domain.Article;
import com.cinepantin.shop.domain.Book;
import com.cinepantin.shop.domain.Dvd;
import com.cinepantin.shop.domain.User;
import com.cinepantin.shop.domain.UserBasket;
import com.cinepantin.shop.domain.VatRate;
import com.cinepantin.shop.service.ArticleService;
import com.cinepantin.shop.service.BookService;


public class Tester {
	
	
	@PersistenceContext(unitName="TestArticlePersistenceUnit")
	private EntityManager em;
    public EntityManager getEm() {
		return em;
	}
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
	/** Cible pour auto-injection Spring : un service d'articles (tous types) **/
	private ArticleService<Article> articleService;
	@Autowired
	@Qualifier(value = "AnyArticleService") // Why ? see http://stackoverflow.com/a/511417/326294
	public void setArticleService(ArticleService<Article> articleService) {
		this.articleService = articleService;
	}
	
	
	/** Cible pour auto-injection Spring : un service d'articles de type Book **/
//	private ArticleService<Book> bookService;
//	@Autowired
//	@Qualifier(value = "BookArticleService") // Why ? see http://stackoverflow.com/a/511417/326294
//	public void setBookService(ArticleService<Book> bookService) {
//		t
	public BookService bookService;
	@Autowired
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	
	
	
	
	

	@Transactional()
    public void test() {

		/*
		ApplicationContext context = 
		    	new ClassPathXmlApplicationContext("com/cinepantin/shop/domain/test/Spring-ApplicationContext.xml");
		*/
    	
    	VatRate vat55 = new VatRate(new BigDecimal("1.055"));
    	em.persist(vat55);
    	VatRate vat186 = new VatRate(new BigDecimal("1.186"));
    	em.persist(vat186);
    	
    	Dvd dvd = new Dvd("Lost Highway", "David Lynch", vat186);
    	dvd.setStockQuantity(2);
    	em.persist(dvd);
		
    	Book book = new Book("Le nom de la Rose", "Umberto Ecco", vat55);
    	book.setStockQuantity(5);
    	System.out.println("book articleId BEFORE persist: " + book.getArticleId());
    	em.persist(book);
    	// em.flush();
    	 System.out.println("book articleId AFTER persist: " + book.getArticleId());
    	 
    	 
     	/*PhysicalBookImpl book = (PhysicalBookImpl) context.getBean(PhysicalBookImpl.class) ;
    	book.setAuthor("Marx");
    	book.setTitle("Le capital");
    	*/
    	
    	 User user = new User();
    	 user.setLogin("myLogin");
    	 user.setPassword("myPassword");
    	 em.persist(user);
    	 
    	 user.setBasket(new UserBasket(user));
    	 user.getBasket().addArticle(book);
    	 user.getBasket().addArticle(dvd);
    	 em.persist(user.getBasket());
    	 
//    	 OrderLine uol = new OrderLine();
//    	 uol.setArticle(book);
//    	 uol.setQuantity(1);
//    	 em.persist(uol);
//    	 System.out.println("Save uol #" + ((OrderLine)uol).getOrderLineId());
    	
    	
//    	TypedQuery<PhysicalBookImpl> tq = em.createQuery("select b from PhysicalBookImpl b", PhysicalBookImpl.class);
//    	List<PhysicalBookImpl> l = tq.getResultList();
//    	if (l.size() > 0 ) {
//	    	for (PhysicalBookImpl b : l) {
//	    		System.out.println(b.getDescription());
//	    	}
//    	} else {
//    		System.out.println("vide...");
//    	}
        
    	TypedQuery<Article> tq = em.createQuery("select a from Article a", Article.class);
    	List<Article> l = tq.getResultList();
        dumpArticleList(l);
    	
    	
    	System.out.println("====== And now for some Spring magic : all articles ========");
    	List<Article> l2 = articleService.findAll();
    	dumpArticleList(l2);
    	
    	
    	System.out.println("====== And now for more Spring magic : all books ========");
    	List<Book> l3 = bookService.findAll();
    	dumpArticleList(l3);
    	
    }
	
	private void dumpArticleList(List<? extends Article> l) {
		if (l.size() > 0 ) { 
	    	for (Article a : l) {
	    		if (a instanceof Book) {
	    			System.out.print(
	    				"Ceci est un livre (l'auteur est " + ((Book) a).getAuthor() + ") -- "
	    			);
	    		}
	    		System.out.println(a.getShortDescription());
	    		VatRate tx = a.getVatRate();
	    		System.out.println("TVA : " + 
	    				(
	    						(tx != null) ?
	    							"x" + tx.getMultiplier()
	    						:
	    							"N/A"	
	    				)
				);	
	    	}
    	} else {
    		System.out.println("vide...");
    	}
	}
	
	
}
