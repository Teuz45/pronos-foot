package fr.cso.models.beans;

public class Equipe {
	private String cdEquipe;

	private String nom;
	
	private int coeffUEFA;
	
	private String logo;
	
	public Equipe(String cdEquipe) {
		super();
		this.cdEquipe = cdEquipe;
	}

	public String getCdEquipe() {
		return cdEquipe;
	}

	public void setCdEquipe(String cdEquipe) {
		this.cdEquipe = cdEquipe;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getCoeffUEFA() {
		return coeffUEFA;
	}

	public void setCoeffUEFA(int coeffUEFA) {
		this.coeffUEFA = coeffUEFA;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	
}
