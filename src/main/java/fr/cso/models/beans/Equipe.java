package fr.cso.models.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="equipes")
@NamedQuery(name="equipes.findAll", query="SELECT e FROM Equipes e")
public class Equipe implements Serializable {
	private static final long serialVersionUID = 1L;
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
