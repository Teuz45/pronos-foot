package fr.cso.core.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.cso.core.IEquipeManager;
import fr.cso.dao.IEquipeDAO;
import fr.cso.models.Equipe;

public class EquipeManager implements IEquipeManager {

	private IEquipeDAO equipeDAO;
	
	@Override
	@Transactional(readOnly=true)
	public List<Equipe> listeEquipes() {
		return equipeDAO.listeEquipes();
	}

	public void setEquipeDAO(IEquipeDAO equipeDAO) {
		this.equipeDAO = equipeDAO;
	}
}
