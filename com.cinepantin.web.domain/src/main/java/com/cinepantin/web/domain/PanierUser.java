/**
 * 
 */
package com.cinepantin.web.domain;

import java.util.List;

/**
 * @author STAGIAIRE
 *
 */
public class PanierUser extends Commande {

	private List<Adresse> adresse;

	public PanierUser(List<Adresse> adresse2) {
		adresse = adresse2;
	}

}
