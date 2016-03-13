package fr.cso.core.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.cso.core.IResultatBo;
import fr.cso.dao.IResultatDAO;
import fr.cso.models.Resultat;

public class ResultatBo implements IResultatBo {

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
