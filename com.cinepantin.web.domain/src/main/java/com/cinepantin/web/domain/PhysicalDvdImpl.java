/**
 * 
 */
package com.cinepantin.web.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author STAGIAIRE
 *
 */
@Entity
@Table(name="Dvd")
public class PhysicalDvdImpl extends PhysicalArticleImpl implements PhysicalDvd {

	public String director;
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}

	public PhysicalDvdImpl() {
		// TODO Auto-generated constructor stub
	}

}
