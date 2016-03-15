package fr.cso.model;

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

	private boolean matchJoue;
	
	private Integer scoreDom;

	private Integer scoreExt;

	private Integer scorePenDom;

	private Integer scorePenExt;

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

	public boolean isMatchJoue() {
		return this.matchJoue;
	}
	
	public void setMatchJoue(boolean matchJoue) {
		this.matchJoue = matchJoue;
	}
	
	public Integer getScoreDom() {
		return this.scoreDom;
	}

	public void setScoreDom(Integer scoreDom) {
		this.scoreDom = scoreDom;
	}

	public Integer getScoreExt() {
		return this.scoreExt;
	}

	public void setScoreExt(Integer scoreExt) {
		this.scoreExt = scoreExt;
	}

	public Integer getScorePenDom() {
		return this.scorePenDom;
	}

	public void setScorePenDom(Integer scorePenDom) {
		this.scorePenDom = scorePenDom;
	}

	public Integer getScorePenExt() {
		return this.scorePenExt;
	}

	public void setScorePenExt(Integer scorePenExt) {
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