package fr.cso.core.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.cso.core.IEquipeBo;
import fr.cso.dao.IEquipeDAO;
import fr.cso.models.Equipe;

public class EquipeBo implements IEquipeBo {

	private IEquipeDAO equipeDAO;
	
	@Override
	@Transactional(readOnly=true)
	public List<Equipe> listEquipes() {
		return equipeDAO.listEquipes();
	}

	public void setEquipeDAO(IEquipeDAO equipeDAO) {
		this.equipeDAO = equipeDAO;
	}
}
