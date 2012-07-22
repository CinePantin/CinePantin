package com.cinepantin.shop.domain;


/**
 * Address (TODO: make Address a Domain Entity)
 *
 */
public class Address  {
	
	private String TODO;
	
	

	private String complement;
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}
	
	
	
	private int numeroRue;
	public int getNumeroRue() {
		return numeroRue;
	}
	public void setNumeroRue(int numeroRue) {
		this.numeroRue = numeroRue;
	}
	
	
	
	private String rue;
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	
	
	
	private String lieuDit;
	public String getLieuDit() {
		return lieuDit;
	}
	public void setLieuDit(String lieuDit) {
		this.lieuDit = lieuDit;
	}
	
	
	
	private int codePostal;
	public int getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	
	
	
	private String ville;
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	
	
	private String pays;
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	
	
	
	public Address() {
		// empty JPA constructor
	}
	
	
	
}
