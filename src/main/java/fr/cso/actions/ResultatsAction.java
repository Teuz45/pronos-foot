package fr.cso.actions;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.apache.bcel.classfile.annotation.ClassElementValueGen;

import fr.cso.core.CalculClassementGroupe;
import fr.cso.core.IMatchManager;
import fr.cso.core.IResultatManager;
import fr.cso.models.Match;
import fr.cso.models.Resultat;
import fr.cso.models.beans.ClassementEquipe;

public class ResultatsAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private IResultatManager resultatManager;
	private IMatchManager matchManager;
	
	private List<Resultat> listeResultats;
	private List<ClassementEquipe> classementGroupe;
	
	public String listerResultats() throws Exception{
		
		init();
		
		listeResultats = resultatManager.listeResultats();
		
		
		List<Match> listeMatchsParGroupe = matchManager.listeMatchsParGroupe("A");
		
		List<Resultat> listeResultatGroupe = new ArrayList<>();
		for(Match match : listeMatchsParGroupe) {
			listeResultatGroupe.add(match.getResultat());
		}
		classementGroupe = CalculClassementGroupe.getClassementGroupe(listeResultatGroupe);
		
		return SUCCESS;
	
	}

	public void setResultatManager(IResultatManager resultatManager) {
		this.resultatManager = resultatManager;
	}

	public List<Resultat> getListeResultats() {
		return listeResultats;
	}

	public void setListeResultats(List<Resultat> listeResultats) {
		this.listeResultats = listeResultats;
	}

	public void setMatchManager(IMatchManager matchManager) {
		this.matchManager = matchManager;
	}

	public List<ClassementEquipe> getClassementGroupe() {
		return classementGroupe;
	}

	public void setClassementGroupe(List<ClassementEquipe> classementGroupe) {
		this.classementGroupe = classementGroupe;
	}	
	
	
}
