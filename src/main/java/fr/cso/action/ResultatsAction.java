package fr.cso.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.cso.core.CalculClassementGroupe;
import fr.cso.core.IResultatManager;
import fr.cso.enums.GroupesEnum;
import fr.cso.model.Match;
import fr.cso.model.Resultat;
import fr.cso.model.bean.ClassementGroupe;

public class ResultatsAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private IResultatManager resultatManager;
	
	private List<Resultat> listeResultats;
	private List<ClassementGroupe> listeClassementsGroupes;
	
	public String listerResultats() throws Exception{
		
		listeResultats = resultatManager.listeResultats();
		
		listeClassementsGroupes = new ArrayList<>();
		for(GroupesEnum groupe : Arrays.asList(GroupesEnum.values())) {
			ClassementGroupe classementGroupe = new ClassementGroupe(groupe);
			List<Match> listeMatchsParGroupe2 = getMatchManager().listeMatchsParGroupe(groupe.getCdGroupe());
			List<Resultat> listeResultatGroupe2 = new ArrayList<>();
			for(Match match : listeMatchsParGroupe2) {
				listeResultatGroupe2.add(match.getResultat());
			}
			classementGroupe.getListeClassementsEquipe().addAll(CalculClassementGroupe.getClassementGroupe(listeResultatGroupe2));
			listeClassementsGroupes.add(classementGroupe);
		}
		
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

	public List<ClassementGroupe> getListeClassementsGroupes() {
		return listeClassementsGroupes;
	}

	public void setListeClassementsGroupes(List<ClassementGroupe> listeClassementsGroupes) {
		this.listeClassementsGroupes = listeClassementsGroupes;
	}	
}
