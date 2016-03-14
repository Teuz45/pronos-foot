package fr.cso.action;

import com.opensymphony.xwork2.ActionSupport;

import fr.cso.model.Equipe;
import fr.cso.model.Match;
import fr.cso.model.Phase;
import fr.cso.model.Resultat;
import fr.cso.model.User;

public class AbstractAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String libelleProchainEvenement;
	private long dateProchainEvenement;
	private Match prochainMatch;
	private User utilisateur;
	
	public void init() {
				
		dateProchainEvenement = 1465585200L;
		
		prochainMatch = new Match();
		prochainMatch.setNumMatch(2);
		Resultat resultat = new Resultat();
		Equipe equipeDom = new Equipe();
		equipeDom.setCdEquipe("FRA");
		equipeDom.setLogo("images/flags/fra.png");
		resultat.setEquipeDom(equipeDom);
		Equipe equipeExt = new Equipe();
		equipeExt.setCdEquipe("ROM");
		equipeExt.setLogo("images/flags/rou.png");
		resultat.setEquipeExt(equipeExt);
		Phase phase = new Phase();
		phase.setCdPhase("GROUPES");
		phase.setLibelle("Groupe");
		prochainMatch.setPhase(phase);
		prochainMatch.setGroupe("A");
		prochainMatch.setResultat(resultat);
		
		if(prochainMatch.getNumMatch()==1) {
			libelleProchainEvenement = "Début de la compétition".toUpperCase();
		}
		else {
			Phase phaseProchainMatch = prochainMatch.getPhase();
			if(phaseProchainMatch.getCdPhase()=="GROUPES") {
				libelleProchainEvenement = phaseProchainMatch.getLibelle() + " " + prochainMatch.getGroupe();
			}
			else {
				libelleProchainEvenement = phaseProchainMatch.getLibelle();
			}
		}
		
		utilisateur = new User();
		utilisateur.setProfil("USER");
	}

	public String getLibelleProchainEvenement() {
		return libelleProchainEvenement;
	}

	public void setLibelleProchainEvenement(String libelleProchainEvenement) {
		this.libelleProchainEvenement = libelleProchainEvenement;
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
	
	
}
