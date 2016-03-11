package fr.cso.actions;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import fr.cso.core.IEquipeBo;
import fr.cso.models.Equipe;

public class HelloWorld extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private IEquipeBo equipeBo;
	
	private List<Equipe> listEquipes;
	
	public String execute() throws Exception {
		setMessage(getText(MESSAGE));
		return SUCCESS;
	}

	//list all customers
	public String listEquipes() throws Exception{
		
		listEquipes = equipeBo.listEquipes();
		
		return "success";
	
	}
	
	/**
	 * Provide default value for Message property.
	 */
	public static final String MESSAGE = "HelloWorld.message";

	/**
	 * Field for Message property.
	 */
	private String message;

	/**
	 * Return Message property.
	 *
	 * @return Message property
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Set Message property.
	 *
	 * @param message
	 *            Text to display on HelloWorld page.
	 */
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