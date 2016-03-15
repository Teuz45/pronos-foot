package fr.cso.model.bean;

import java.util.ArrayList;
import java.util.List;

import fr.cso.enums.GroupesEnum;

public class ClassementGroupe {

	private GroupesEnum groupe;
		
	private List<ClassementEquipe> listeClassementsEquipe;

	public ClassementGroupe(GroupesEnum groupe) {
		this.groupe = groupe;
		setListeClassementsEquipe(new ArrayList<ClassementEquipe>());
	}

	public GroupesEnum getGroupe() {
		return groupe;
	}

	public void setGroupe(GroupesEnum groupe) {
		this.groupe = groupe;
	}

	public List<ClassementEquipe> getListeClassementsEquipe() {
		return listeClassementsEquipe;
	}

	public void setListeClassementsEquipe(List<ClassementEquipe> listeClassementsEquipe) {
		this.listeClassementsEquipe = listeClassementsEquipe;
	}
	
	
}
