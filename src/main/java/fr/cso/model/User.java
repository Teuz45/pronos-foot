package fr.cso.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String login;

	private String email;

	private String nom;

	private String prenom;

	private String profil;

	//bi-directional many-to-one association to Bonus
	@OneToMany(mappedBy="userBean")
	private List<Bonus> bonuses;

	//bi-directional many-to-one association to PronosticMatch
	@OneToMany(mappedBy="userBean")
	private List<PronosticMatch> pronosticsmatchs;

	public User() {
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getProfil() {
		return this.profil;
	}

	public void setProfil(String profil) {
		this.profil = profil;
	}

	public List<Bonus> getBonuses() {
		return this.bonuses;
	}

	public void setBonuses(List<Bonus> bonuses) {
		this.bonuses = bonuses;
	}

	public Bonus addBonus(Bonus bonus) {
		getBonuses().add(bonus);
		bonus.setUserBean(this);

		return bonus;
	}

	public Bonus removeBonus(Bonus bonus) {
		getBonuses().remove(bonus);
		bonus.setUserBean(null);

		return bonus;
	}

	public List<PronosticMatch> getPronosticsmatchs() {
		return this.pronosticsmatchs;
	}

	public void setPronosticsmatchs(List<PronosticMatch> pronosticsmatchs) {
		this.pronosticsmatchs = pronosticsmatchs;
	}

	public PronosticMatch addPronosticsmatch(PronosticMatch pronosticsmatch) {
		getPronosticsmatchs().add(pronosticsmatch);
		pronosticsmatch.setUserBean(this);

		return pronosticsmatch;
	}

	public PronosticMatch removePronosticsmatch(PronosticMatch pronosticsmatch) {
		getPronosticsmatchs().remove(pronosticsmatch);
		pronosticsmatch.setUserBean(null);

		return pronosticsmatch;
	}

}