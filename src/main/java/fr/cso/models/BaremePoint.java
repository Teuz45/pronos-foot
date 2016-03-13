package fr.cso.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the baremespoints database table.
 * 
 */
@Entity
@Table(name="baremespoints")
@NamedQuery(name="BaremePoint.findAll", query="SELECT b FROM BaremePoint b")
public class BaremePoint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cdBareme;

	private int nbPoints;

	public BaremePoint() {
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