package fr.cso.model;

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

	//bi-directional many-to-one association to Phase
	@ManyToOne
	@JoinColumn(name="phase")
	private Phase phase;

	//bi-directional many-to-one association to Stade
	@ManyToOne
	@JoinColumn(name="localisation")
	private Stade stade;

	//bi-directional many-to-one association to PronosticMatch
	@OneToMany(mappedBy="matchBean")
	private List<PronosticMatch> pronosticsmatchs;

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

	public Phase getPhase() {
		return this.phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public Stade getStade() {
		return this.stade;
	}

	public void setStade(Stade stade) {
		this.stade = stade;
	}

	public List<PronosticMatch> getPronosticsmatchs() {
		return this.pronosticsmatchs;
	}

	public void setPronosticsmatchs(List<PronosticMatch> pronosticsmatchs) {
		this.pronosticsmatchs = pronosticsmatchs;
	}

	public PronosticMatch addPronosticsmatch(PronosticMatch pronosticsmatch) {
		getPronosticsmatchs().add(pronosticsmatch);
		pronosticsmatch.setMatchBean(this);

		return pronosticsmatch;
	}

	public PronosticMatch removePronosticsmatch(PronosticMatch pronosticsmatch) {
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