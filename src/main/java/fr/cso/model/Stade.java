package fr.cso.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the stades database table.
 * 
 */
@Entity
@Table(name="stades")
@NamedQuery(name="Stade.findAll", query="SELECT s FROM Stade s")
public class Stade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idStade;

	private String nomStade;

	private String pays;

	private String ville;

	//bi-directional many-to-one association to Match
	@OneToMany(mappedBy="stade")
	private List<Match> matchs;

	public Stade() {
	}

	public int getIdStade() {
		return this.idStade;
	}

	public void setIdStade(int idStade) {
		this.idStade = idStade;
	}

	public String getNomStade() {
		return this.nomStade;
	}

	public void setNomStade(String nomStade) {
		this.nomStade = nomStade;
	}

	public String getPays() {
		return this.pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public List<Match> getMatchs() {
		return this.matchs;
	}

	public void setMatchs(List<Match> matchs) {
		this.matchs = matchs;
	}

	public Match addMatch(Match match) {
		getMatchs().add(match);
		match.setStade(this);

		return match;
	}

	public Match removeMatch(Match match) {
		getMatchs().remove(match);
		match.setStade(null);

		return match;
	}

}