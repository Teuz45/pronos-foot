package fr.cso.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pronosticsmatchs database table.
 * 
 */
@Entity
@Table(name="pronosticsmatchs")
@NamedQuery(name="PronosticMatch.findAll", query="SELECT p FROM PronosticMatch p")
public class PronosticMatch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idPronosticMatch;

	private int nbPointsMatch;

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

	//bi-directional many-to-one association to Match
	@ManyToOne
	@JoinColumn(name="match")
	private Match matchBean;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user")
	private User userBean;

	public PronosticMatch() {
	}

	public int getIdPronosticMatch() {
		return this.idPronosticMatch;
	}

	public void setIdPronosticMatch(int idPronosticMatch) {
		this.idPronosticMatch = idPronosticMatch;
	}

	public int getNbPointsMatch() {
		return this.nbPointsMatch;
	}

	public void setNbPointsMatch(int nbPointsMatch) {
		this.nbPointsMatch = nbPointsMatch;
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

	public Match getMatchBean() {
		return this.matchBean;
	}

	public void setMatchBean(Match matchBean) {
		this.matchBean = matchBean;
	}

	public User getUserBean() {
		return this.userBean;
	}

	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

}