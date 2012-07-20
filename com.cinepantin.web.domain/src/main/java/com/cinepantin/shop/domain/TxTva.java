/**
 * 
 */
package com.cinepantin.shop.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @author STAGIAIRE
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class TxTva {

	private int idTva;

	 int getIdTva() {
		return 0;
	}

	public void setIdTva(int idTva) {
		this.idTva = idTva;
	}

}
