package fr.cso.core.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.cso.core.IResultatManager;
import fr.cso.dao.IResultatDAO;
import fr.cso.models.Resultat;

public class ResultatManager implements IResultatManager {

	private IResultatDAO resultatDAO;
	
	@Override
	@Transactional(readOnly=true)
	public List<Resultat> listeResultats() {
		return resultatDAO.listeResultats();
	}

	public void setResultatDAO(IResultatDAO resultatDAO) {
		this.resultatDAO = resultatDAO;
	}

}
