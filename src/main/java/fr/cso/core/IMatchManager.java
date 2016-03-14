package fr.cso.core;

import java.util.List;

import fr.cso.models.Match;

public interface IMatchManager {
	List<Match> listeMatchs();
	
	List<Match> listeMatchsParGroupe(String codeGroupe);
}
