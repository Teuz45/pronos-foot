package fr.cso.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import fr.cso.core.IMatchManager;
import fr.cso.model.Match;
import fr.cso.model.User;

public class AbstractAction extends ActionSupport implements Preparable {

	private static final long serialVersionUID = 1L;

	private IMatchManager matchManager;
	
	private long dateProchainEvenement;
	private Match prochainMatch;
	private User utilisateur;
	
	@Override
	public void prepare() throws Exception {
		prochainMatch = matchManager.getProchainMatch();
		
		dateProchainEvenement = prochainMatch.getDate().getTime();
		
		utilisateur = new User();
		utilisateur.setProfil("USER");
		
	}

	public long getDateProchainEvenement() {
		return dateProchainEvenement;
	}

	public void setDateProchainEvenement(long dateProchainEvenement) {
		this.dateProchainEvenement = dateProchainEvenement;
	}

	public Match getProchainMatch() {
		return prochainMatch;
	}

	public void setProchainMatch(Match prochainMatch) {
		this.prochainMatch = prochainMatch;
	}

	public User getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(User utilisateur) {
		this.utilisateur = utilisateur;
	}

	public void setMatchManager(IMatchManager matchManager) {
		this.matchManager = matchManager;
	}

	public IMatchManager getMatchManager() {
		return matchManager;
	}

	
}
