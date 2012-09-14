package com.cinepantin.shop.domain;

import javax.persistence.Embeddable;

@Embeddable
public class AddressFields {
	
	
	
//	private String FriendlyNameEmb;
//	public String getFriendlyNameEmb() {
//		return FriendlyNameEmb;
//	}
//	public void setFriendlyNameEmb(String friendlyNameEmb) {
//		FriendlyNameEmb = friendlyNameEmb;
//	}
	
	
	
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
	
	
	
	/** JPA empty constructor */
	public AddressFields() {}
	
	
}
