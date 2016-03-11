package fr.cso.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the baremespoints database table.
 * 
 */
@Entity
@Table(name="baremespoints")
@NamedQuery(name="Baremespoint.findAll", query="SELECT b FROM Baremespoint b")
public class Baremespoint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cdBareme;

	private int nbPoints;

	public Baremespoint() {
	}

	public String getCdBareme() {
		return this.cdBareme;
	}

	public void setCdBareme(String cdBareme) {
		this.cdBareme = cdBareme;
	}

	public int getNbPoints() {
		return this.nbPoints;
	}

	public void setNbPoints(int nbPoints) {
		this.nbPoints = nbPoints;
	}

}