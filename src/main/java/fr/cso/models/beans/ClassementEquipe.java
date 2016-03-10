package fr.cso.models.beans;

import java.util.Comparator;

public class ClassementEquipe implements Comparable<ClassementEquipe> {
	private String nomEquipe;
	
	private int ranking;
	
	private int nbPoints = 0;
	
	private int nbMatchsJoues = 0;
	
	private int nbVictoires = 0;
	
	private int nbNuls = 0;
	
	private int nbDefaites = 0;
	
	private int nbVictoiresDom = 0;
	
	private int nbNulsDom = 0;
	
	private int nbDefaitesDom = 0;

	private int nbVictoiresExt = 0;
	
	private int nbNulsExt = 0;
	
	private int nbDefaitesExt = 0;
	
	private int nbButsPour = 0;
	
	private int nbButsContre = 0;
	
	private int differenceButs = 0;
	
	private static Comparator<ClassementEquipe> comparatorClassementGeneralPoule = new Comparator<ClassementEquipe>() {
	    public int compare(ClassementEquipe classementEq1, ClassementEquipe classementEq2) {
	        return classementEq1.compareTo(classementEq2);
	    }
	};
	
	public ClassementEquipe(String nomEquipe) {
		super();
		this.nomEquipe = nomEquipe;
	}

	public int compareTo(ClassementEquipe compareClassement) {
		return compareClassement.getNbPoints() - this.getNbPoints();
	}
	
	public String getNomEquipe() {
		return nomEquipe;
	}
	
	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public void setNomEquipe(String nomEquipe) {
		this.nomEquipe = nomEquipe;
	}

	public int getNbPoints() {
		return nbPoints;
	}

	public void setNbPoints(int nbPoints) {
		this.nbPoints = nbPoints;
	}

	public int getNbMatchsJoues() {
		return nbMatchsJoues;
	}

	public void setNbMatchsJoues(int nbMatchsJoues) {
		this.nbMatchsJoues = nbMatchsJoues;
	}

	public int getNbVictoires() {
		return nbVictoires;
	}

	public void setNbVictoires(int nbVictoires) {
		this.nbVictoires = nbVictoires;
	}

	public int getNbNuls() {
		return nbNuls;
	}

	public void setNbNuls(int nbNuls) {
		this.nbNuls = nbNuls;
	}

	public int getNbDefaites() {
		return nbDefaites;
	}

	public void setNbDefaites(int nbDefaites) {
		this.nbDefaites = nbDefaites;
	}

	public int getNbVictoiresDom() {
		return nbVictoiresDom;
	}

	public void setNbVictoiresDom(int nbVictoiresDom) {
		this.nbVictoiresDom = nbVictoiresDom;
	}

	public int getNbNulsDom() {
		return nbNulsDom;
	}

	public void setNbNulsDom(int nbNulsDom) {
		this.nbNulsDom = nbNulsDom;
	}

	public int getNbDefaitesDom() {
		return nbDefaitesDom;
	}

	public void setNbDefaitesDom(int nbDefaitesDom) {
		this.nbDefaitesDom = nbDefaitesDom;
	}

	public int getNbVictoiresExt() {
		return nbVictoiresExt;
	}

	public void setNbVictoiresExt(int nbVictoiresExt) {
		this.nbVictoiresExt = nbVictoiresExt;
	}

	public int getNbNulsExt() {
		return nbNulsExt;
	}

	public void setNbNulsExt(int nbNulsExt) {
		this.nbNulsExt = nbNulsExt;
	}

	public int getNbDefaitesExt() {
		return nbDefaitesExt;
	}

	public void setNbDefaitesExt(int nbDefaitesExt) {
		this.nbDefaitesExt = nbDefaitesExt;
	}

	public int getNbButsPour() {
		return nbButsPour;
	}

	public void setNbButsPour(int nbButsPour) {
		this.nbButsPour = nbButsPour;
	}

	public int getNbButsContre() {
		return nbButsContre;
	}

	public void setNbButsContre(int nbButsContre) {
		this.nbButsContre = nbButsContre;
	}

	public int getDifferenceButs() {
		return differenceButs;
	}

	public void setDifferenceButs(int differenceButs) {
		this.differenceButs = differenceButs;
	}
	
	public static Comparator<ClassementEquipe> getComparatorClassementPoule() {
		return comparatorClassementGeneralPoule;
	}

	@Override
	public String toString() {
		
		StringBuilder str = new StringBuilder();
		str.append(this.getRanking() + "\t");
		str.append(this.getNomEquipe() + "\t");
		str.append(this.getNbPoints() + "\t");
		str.append(this.getNbMatchsJoues() + "\t");
		str.append(this.getNbVictoiresDom() + "\t");
		str.append(this.getNbNulsDom() + "\t");
		str.append(this.getNbDefaitesDom() + "\t");
		str.append(this.getNbVictoiresExt() + "\t");
		str.append(this.getNbNulsExt() + "\t");
		str.append(this.getNbDefaitesExt() + "\t");
		str.append(this.getNbVictoires() + "\t");
		str.append(this.getNbNuls() + "\t");
		str.append(this.getNbDefaites() + "\t");
		str.append(this.getNbButsPour() + "\t");
		str.append(this.getNbButsContre() + "\t");
		str.append(this.getDifferenceButs() + "\t");
		
		return str.toString();
	}	
	
	
}
