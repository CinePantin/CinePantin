/**
 * 
 */
package com.cinepantin.web.domain;

import java.util.List;

/**
 * @author STAGIAIRE
 *
 */
public class Panier extends PanierUser {

	private List<Article> article;

	public Panier(List<Article> article2) {
		article = article2;
	}

}
