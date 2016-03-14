package fr.cso.core.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.cso.core.IMatchManager;
import fr.cso.dao.IMatchDAO;
import fr.cso.model.Match;

public class MatchManager implements IMatchManager {

	private IMatchDAO matchDAO;
	
	@Override
	@Transactional(readOnly=true)
	public List<Match> listeMatchs() {
		return matchDAO.listeMatchs();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Match> listeMatchsParGroupe(String codeGroupe) {
		return matchDAO.listeMatchsParGroupe(codeGroupe);
	}
	
	public void setMatchDAO(IMatchDAO matchDAO) {
		this.matchDAO = matchDAO;
	}
	
}
