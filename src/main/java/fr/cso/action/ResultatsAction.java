package fr.cso.action;

import java.util.ArrayList;
import java.util.List;

import fr.cso.core.CalculClassementGroupe;
import fr.cso.core.IResultatManager;
import fr.cso.model.Match;
import fr.cso.model.Resultat;
import fr.cso.model.bean.ClassementEquipe;

public class ResultatsAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private IResultatManager resultatManager;
//	private IMatchManager matchManager;
	
	private List<Resultat> listeResultats;
	private List<ClassementEquipe> classementGroupe;
	
	public String listerResultats() throws Exception{
		
		listeResultats = resultatManager.listeResultats();
		
		
		List<Match> listeMatchsParGroupe = getMatchManager().listeMatchsParGroupe("A");
		
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

//	public void setMatchManager(IMatchManager matchManager) {
//		this.matchManager = matchManager;
//	}

	public List<ClassementEquipe> getClassementGroupe() {
		return classementGroupe;
	}

	public void setClassementGroupe(List<ClassementEquipe> classementGroupe) {
		this.classementGroupe = classementGroupe;
	}	
	
	
}
