package fr.cso.core.impl;

import java.util.List;

import fr.cso.core.IEquipeBo;
import fr.cso.dao.IEquipeDAO;
import fr.cso.models.beans.Equipe;

public class EquipeBo implements IEquipeBo {

	private IEquipeDAO equipeDAO;
	
	@Override
	public List<Equipe> listEquipes() {
		return equipeDAO.listEquipes();
	}

	public void setEquipeDAO(IEquipeDAO equipeDAO) {
		this.equipeDAO = equipeDAO;
	}
}
