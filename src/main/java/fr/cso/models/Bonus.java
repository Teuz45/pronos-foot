package fr.cso.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bonus database table.
 * 
 */
@Entity
@NamedQuery(name="Bonus.findAll", query="SELECT b FROM Bonus b")
public class Bonus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idBonus;

	private String cdBonus;

	private int nbPointsBonus;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user")
	private User userBean;

	public Bonus() {
	}

	public int getIdBonus() {
		return this.idBonus;
	}

	public void setIdBonus(int idBonus) {
		this.idBonus = idBonus;
	}

	public String getCdBonus() {
		return this.cdBonus;
	}

	public void setCdBonus(String cdBonus) {
		this.cdBonus = cdBonus;
	}

	public int getNbPointsBonus() {
		return this.nbPointsBonus;
	}

	public void setNbPointsBonus(int nbPointsBonus) {
		this.nbPointsBonus = nbPointsBonus;
	}

	public User getUserBean() {
		return this.userBean;
	}

	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

}