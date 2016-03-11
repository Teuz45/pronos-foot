package fr.cso.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the equipes database table.
 * 
 */
@Entity
@Table(name="equipes")
@NamedQuery(name="Equipe.findAll", query="SELECT e FROM Equipe e")
public class Equipe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cdEquipe;

	private int coeffUEFA;

	private String logo;

	private String nom;

	//bi-directional many-to-one association to Pronosticsmatch
	@OneToMany(mappedBy="equipeExt")
	private List<Pronosticsmatch> pronosticsmatchs1;

	//bi-directional many-to-one association to Pronosticsmatch
	@OneToMany(mappedBy="equipeDom")
	private List<Pronosticsmatch> pronosticsmatchs2;

	//bi-directional many-to-one association to Resultat
	@OneToMany(mappedBy="equipeExt")
	private List<Resultat> resultats1;

	//bi-directional many-to-one association to Resultat
	@OneToMany(mappedBy="equipeDom")
	private List<Resultat> resultats2;

	public Equipe() {
	}

	public String getCdEquipe() {
		return this.cdEquipe;
	}

	public void setCdEquipe(String cdEquipe) {
		this.cdEquipe = cdEquipe;
	}

	public int getCoeffUEFA() {
		return this.coeffUEFA;
	}

	public void setCoeffUEFA(int coeffUEFA) {
		this.coeffUEFA = coeffUEFA;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Pronosticsmatch> getPronosticsmatchs1() {
		return this.pronosticsmatchs1;
	}

	public void setPronosticsmatchs1(List<Pronosticsmatch> pronosticsmatchs1) {
		this.pronosticsmatchs1 = pronosticsmatchs1;
	}

	public Pronosticsmatch addPronosticsmatchs1(Pronosticsmatch pronosticsmatchs1) {
		getPronosticsmatchs1().add(pronosticsmatchs1);
		pronosticsmatchs1.setEquipeExt(this);

		return pronosticsmatchs1;
	}

	public Pronosticsmatch removePronosticsmatchs1(Pronosticsmatch pronosticsmatchs1) {
		getPronosticsmatchs1().remove(pronosticsmatchs1);
		pronosticsmatchs1.setEquipeExt(null);

		return pronosticsmatchs1;
	}

	public List<Pronosticsmatch> getPronosticsmatchs2() {
		return this.pronosticsmatchs2;
	}

	public void setPronosticsmatchs2(List<Pronosticsmatch> pronosticsmatchs2) {
		this.pronosticsmatchs2 = pronosticsmatchs2;
	}

	public Pronosticsmatch addPronosticsmatchs2(Pronosticsmatch pronosticsmatchs2) {
		getPronosticsmatchs2().add(pronosticsmatchs2);
		pronosticsmatchs2.setEquipeDom(this);

		return pronosticsmatchs2;
	}

	public Pronosticsmatch removePronosticsmatchs2(Pronosticsmatch pronosticsmatchs2) {
		getPronosticsmatchs2().remove(pronosticsmatchs2);
		pronosticsmatchs2.setEquipeDom(null);

		return pronosticsmatchs2;
	}

	public List<Resultat> getResultats1() {
		return this.resultats1;
	}

	public void setResultats1(List<Resultat> resultats1) {
		this.resultats1 = resultats1;
	}

	public Resultat addResultats1(Resultat resultats1) {
		getResultats1().add(resultats1);
		resultats1.setEquipeExt(this);

		return resultats1;
	}

	public Resultat removeResultats1(Resultat resultats1) {
		getResultats1().remove(resultats1);
		resultats1.setEquipeExt(null);

		return resultats1;
	}

	public List<Resultat> getResultats2() {
		return this.resultats2;
	}

	public void setResultats2(List<Resultat> resultats2) {
		this.resultats2 = resultats2;
	}

	public Resultat addResultats2(Resultat resultats2) {
		getResultats2().add(resultats2);
		resultats2.setEquipeDom(this);

		return resultats2;
	}

	public Resultat removeResultats2(Resultat resultats2) {
		getResultats2().remove(resultats2);
		resultats2.setEquipeDom(null);

		return resultats2;
	}

}