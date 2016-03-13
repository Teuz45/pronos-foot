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

	//bi-directional many-to-one association to PronosticMatch
	@OneToMany(mappedBy="equipeExt")
	private List<PronosticMatch> pronosticsmatchsext;

	//bi-directional many-to-one association to PronosticMatch
	@OneToMany(mappedBy="equipeDom")
	private List<PronosticMatch> pronosticsmatchsdom;

	//bi-directional many-to-one association to Resultat
	@OneToMany(mappedBy="equipeExt")
	private List<Resultat> resultatsExt;

	//bi-directional many-to-one association to Resultat
	@OneToMany(mappedBy="equipeDom")
	private List<Resultat> resultatsDom;

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

	public List<PronosticMatch> getPronosticsmatchsext() {
		return this.pronosticsmatchsext;
	}

	public void setPronosticsmatchsext(List<PronosticMatch> pronosticsmatchsext) {
		this.pronosticsmatchsext = pronosticsmatchsext;
	}

	public PronosticMatch addPronosticsmatchsext(PronosticMatch pronosticsmatchsext) {
		getPronosticsmatchsext().add(pronosticsmatchsext);
		pronosticsmatchsext.setEquipeExt(this);

		return pronosticsmatchsext;
	}

	public PronosticMatch removePronosticsmatchsext(PronosticMatch pronosticsmatchsext) {
		getPronosticsmatchsext().remove(pronosticsmatchsext);
		pronosticsmatchsext.setEquipeExt(null);

		return pronosticsmatchsext;
	}

	public List<PronosticMatch> getPronosticsmatchsdom() {
		return this.pronosticsmatchsdom;
	}

	public void setPronosticsmatchsdom(List<PronosticMatch> pronosticsmatchsdom) {
		this.pronosticsmatchsdom = pronosticsmatchsdom;
	}

	public PronosticMatch addPronosticsmatchsdom(PronosticMatch pronosticsmatchsdom) {
		getPronosticsmatchsdom().add(pronosticsmatchsdom);
		pronosticsmatchsdom.setEquipeDom(this);

		return pronosticsmatchsdom;
	}

	public PronosticMatch removePronosticsmatchsdom(PronosticMatch pronosticsmatchsdom) {
		getPronosticsmatchsdom().remove(pronosticsmatchsdom);
		pronosticsmatchsdom.setEquipeDom(null);

		return pronosticsmatchsdom;
	}

	public List<Resultat> getResultatsExt() {
		return this.resultatsExt;
	}

	public void setResultatsExt(List<Resultat> resultatsExt) {
		this.resultatsExt = resultatsExt;
	}

	public Resultat addResultatsExt(Resultat resultatsExt) {
		getResultatsExt().add(resultatsExt);
		resultatsExt.setEquipeExt(this);

		return resultatsExt;
	}

	public Resultat removeResultatsExt(Resultat resultatsExt) {
		getResultatsExt().remove(resultatsExt);
		resultatsExt.setEquipeExt(null);

		return resultatsExt;
	}

	public List<Resultat> getResultatsDom() {
		return this.resultatsDom;
	}

	public void setResultatsDom(List<Resultat> resultatsDom) {
		this.resultatsDom = resultatsDom;
	}

	public Resultat addResultatsDom(Resultat resultatsDom) {
		getResultatsDom().add(resultatsDom);
		resultatsDom.setEquipeDom(this);

		return resultatsDom;
	}

	public Resultat removeResultatsDom(Resultat resultatsDom) {
		getResultatsDom().remove(resultatsDom);
		resultatsDom.setEquipeDom(null);

		return resultatsDom;
	}

}