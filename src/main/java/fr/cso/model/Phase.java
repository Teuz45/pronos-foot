package fr.cso.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the phases database table.
 * 
 */
@Entity
@Table(name="phases")
@NamedQuery(name="Phase.findAll", query="SELECT p FROM Phase p")
public class Phase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cdPhase;

	private String libelle;

	//bi-directional many-to-one association to Match
	@OneToMany(mappedBy="phase")
	private List<Match> matchs;

	public Phase() {
	}

	public String getCdPhase() {
		return this.cdPhase;
	}

	public void setCdPhase(String cdPhase) {
		this.cdPhase = cdPhase;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Match> getMatchs() {
		return this.matchs;
	}

	public void setMatchs(List<Match> matchs) {
		this.matchs = matchs;
	}

	public Match addMatch(Match match) {
		getMatchs().add(match);
		match.setPhase(this);

		return match;
	}

	public Match removeMatch(Match match) {
		getMatchs().remove(match);
		match.setPhase(null);

		return match;
	}

}