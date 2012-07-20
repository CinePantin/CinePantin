/**
 * 
 */
package com.cinepantin.shop.domain;

import java.util.List;

/**
 * @author STAGIAIRE
 *
 */
public class Adresse  {

	private List<PanierUser> panierUser;
	private String complement;
	private int numeroRue;
	private String rue;
	private String lieuDit;
	private int codePostal;
	private String ville;
	private String pays;
	
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}
	public int getNumeroRue() {
		return numeroRue;
	}
	public void setNumeroRue(int numeroRue) {
		this.numeroRue = numeroRue;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getLieuDit() {
		return lieuDit;
	}
	public void setLieuDit(String lieuDit) {
		this.lieuDit = lieuDit;
	}
	public int getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public Adresse() {
		// empty
	}
	
	
	
}
