package fr.cso.actions;

import java.util.List;

import fr.cso.core.IEquipeBo;
import fr.cso.models.Equipe;

public class HelloWorld extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private IEquipeBo equipeBo;
	
	private List<Equipe> listEquipes;
	private String message;

	
	public String execute() throws Exception {
		return SUCCESS;
	}

	//list all customers
	public String listeEquipes() throws Exception{
		init();
		
		setMessage(getText(MESSAGE));
		
		listEquipes = equipeBo.listeEquipes();
		
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
	
}