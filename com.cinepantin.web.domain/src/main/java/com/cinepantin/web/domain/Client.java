/**
 * 
 */
package com.cinepantin.web.domain;

import java.util.List;

/**
 * @author STAGIAIRE
 *
 */
public class Client extends Administrateur {

	private List<Commande> commande;

	public Client(List<Commande> commande2) {
		commande = commande2;
		// TODO Auto-generated constructor stub
	}

}
