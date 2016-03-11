package fr.cso.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the matchs database table.
 * 
 */
@Entity
@Table(name="matchs")
@NamedQuery(name="Match.findAll", query="SELECT m FROM Match m")
public class Match implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int numMatch;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String groupe;

	//bi-directional many-to-one association to Phas
	@ManyToOne
	@JoinColumn(name="phase")
	private Phas phas;

	//bi-directional many-to-one association to Stade
	@ManyToOne
	@JoinColumn(name="localisation")
	private Stade stade;

	//bi-directional many-to-one association to Pronosticsmatch
	@OneToMany(mappedBy="matchBean")
	private List<Pronosticsmatch> pronosticsmatchs;

	//bi-directional one-to-one association to Resultat
	@OneToOne(mappedBy="match")
	private Resultat resultat;

	public Match() {
	}

	public int getNumMatch() {
		return this.numMatch;
	}

	public void setNumMatch(int numMatch) {
		this.numMatch = numMatch;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getGroupe() {
		return this.groupe;
	}

	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

	public Phas getPhas() {
		return this.phas;
	}

	public void setPhas(Phas phas) {
		this.phas = phas;
	}

	public Stade getStade() {
		return this.stade;
	}

	public void setStade(Stade stade) {
		this.stade = stade;
	}

	public List<Pronosticsmatch> getPronosticsmatchs() {
		return this.pronosticsmatchs;
	}

	public void setPronosticsmatchs(List<Pronosticsmatch> pronosticsmatchs) {
		this.pronosticsmatchs = pronosticsmatchs;
	}

	public Pronosticsmatch addPronosticsmatch(Pronosticsmatch pronosticsmatch) {
		getPronosticsmatchs().add(pronosticsmatch);
		pronosticsmatch.setMatchBean(this);

		return pronosticsmatch;
	}

	public Pronosticsmatch removePronosticsmatch(Pronosticsmatch pronosticsmatch) {
		getPronosticsmatchs().remove(pronosticsmatch);
		pronosticsmatch.setMatchBean(null);

		return pronosticsmatch;
	}

	public Resultat getResultat() {
		return this.resultat;
	}

	public void setResultat(Resultat resultat) {
		this.resultat = resultat;
	}

}