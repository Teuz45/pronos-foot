package fr.cso.core;

import java.util.Date;
import java.util.List;

import fr.cso.model.Match;

public interface IMatchManager {
	List<Match> listeMatchs();
	
	List<Match> listeMatchsParGroupe(String codeGroupe);
	
	Match getProchainMatch();
}
