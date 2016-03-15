package fr.cso.action;

import java.util.List;

import fr.cso.core.IEquipeManager;
import fr.cso.model.Equipe;

public class HelloWorld extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private IEquipeManager equipeManager;
	
	private List<Equipe> listEquipes;
	private String message;

	
	public String execute() throws Exception {
		return SUCCESS;
	}

	//list all customers
	public String listeEquipes() throws Exception{
		setMessage(getText(MESSAGE));
		
		listEquipes = equipeManager.listeEquipes();
		
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

	public void setEquipeManager(IEquipeManager equipeManager) {
		this.equipeManager = equipeManager;
	}
	
}