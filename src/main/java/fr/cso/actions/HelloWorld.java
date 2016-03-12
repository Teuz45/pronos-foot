package fr.cso.actions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import fr.cso.core.IEquipeBo;
import fr.cso.models.Equipe;
import fr.cso.models.Match;
import fr.cso.models.Resultat;

public class HelloWorld extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private IEquipeBo equipeBo;
	
	private List<Equipe> listEquipes;
	private String message;
	private String libelleProchainEvenement;
	private long dateProchainEvenement;
	private Match prochainMatch;
	
	public String execute() throws Exception {
		return SUCCESS;
	}

	//list all customers
	public String listEquipes() throws Exception{
		
		setMessage(getText(MESSAGE));
		
		libelleProchainEvenement = "Début de la compétition".toUpperCase();
		
		dateProchainEvenement = 1465585200L;
		
		prochainMatch = new Match();
		Resultat resultat = new Resultat();
		Equipe equipeDom = new Equipe();
		equipeDom.setCdEquipe("FRA");
		equipeDom.setLogo("images/flags/fra.png");
		resultat.setEquipeDom(equipeDom);
		Equipe equipeExt = new Equipe();
		equipeExt.setCdEquipe("ROM");
		equipeExt.setLogo("images/flags/rou.png");
		resultat.setEquipeExt(equipeExt);
		prochainMatch.setResultat(resultat);
		
		listEquipes = equipeBo.listEquipes();
		
		return SUCCESS;
	
	}
	
	public static final String MESSAGE = "HelloWorld.message";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<Equipe> getListEquipes() {
		return listEquipes;
	}

	public void setListEquipes(List<Equipe> listEquipes) {
		this.listEquipes = listEquipes;
	}

	public void setEquipeBo(IEquipeBo equipeBo) {
		this.equipeBo = equipeBo;
	}

	public String getLibelleProchainEvenement() {
		return libelleProchainEvenement;
	}

	public void setLibelleProchainEvenement(String libelleProchainEvenement) {
		this.libelleProchainEvenement = libelleProchainEvenement;
	}

	public Long getDateProchainEvenement() {
		return dateProchainEvenement;
	}

	public void setDateProchainEvenement(Long dateProchainEvenement) {
		this.dateProchainEvenement = dateProchainEvenement;
	}

	public Match getProchainMatch() {
		return prochainMatch;
	}

	public void setProchainMatch(Match prochainMatch) {
		this.prochainMatch = prochainMatch;
	}

	public void setDateProchainEvenement(long dateProchainEvenement) {
		this.dateProchainEvenement = dateProchainEvenement;
	}
	
	
}