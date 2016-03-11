package fr.cso.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the resultats database table.
 * 
 */
@Entity
@Table(name="resultats")
@NamedQuery(name="Resultat.findAll", query="SELECT r FROM Resultat r")
public class Resultat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idMatch;

	private int scoreDom;

	private int scoreExt;

	private int scorePenDom;

	private int scorePenExt;

	//bi-directional many-to-one association to Equipe
	@ManyToOne
	@JoinColumn(name="equipeExt")
	private Equipe equipeExt;

	//bi-directional many-to-one association to Equipe
	@ManyToOne
	@JoinColumn(name="equipeDom")
	private Equipe equipeDom;

	//bi-directional one-to-one association to Match
	@OneToOne
	@JoinColumn(name="idMatch")
	private Match match;

	public Resultat() {
	}

	public int getIdMatch() {
		return this.idMatch;
	}

	public void setIdMatch(int idMatch) {
		this.idMatch = idMatch;
	}

	public int getScoreDom() {
		return this.scoreDom;
	}

	public void setScoreDom(int scoreDom) {
		this.scoreDom = scoreDom;
	}

	public int getScoreExt() {
		return this.scoreExt;
	}

	public void setScoreExt(int scoreExt) {
		this.scoreExt = scoreExt;
	}

	public int getScorePenDom() {
		return this.scorePenDom;
	}

	public void setScorePenDom(int scorePenDom) {
		this.scorePenDom = scorePenDom;
	}

	public int getScorePenExt() {
		return this.scorePenExt;
	}

	public void setScorePenExt(int scorePenExt) {
		this.scorePenExt = scorePenExt;
	}

	public Equipe getEquipeExt() {
		return this.equipeExt;
	}

	public void setEquipeExt(Equipe equipeExt) {
		this.equipeExt = equipeExt;
	}

	public Equipe getEquipeDom() {
		return this.equipeDom;
	}

	public void setEquipeDom(Equipe equipeDom) {
		this.equipeDom = equipeDom;
	}

	public Match getMatch() {
		return this.match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

}