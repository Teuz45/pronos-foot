package fr.cso.dao;

import java.util.List;

import fr.cso.models.Match;

public interface IMatchDAO {
	List<Match> listeMatchs();
	
	List<Match> listeMatchsParGroupe(String codeGroupe);
}
