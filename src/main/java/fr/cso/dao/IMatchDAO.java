package fr.cso.dao;

import java.util.Date;
import java.util.List;

import fr.cso.model.Match;

public interface IMatchDAO {
	List<Match> listeMatchs();
	
	List<Match> listeMatchsParGroupe(String codeGroupe);
	
	Match getProchainMatch();
}
