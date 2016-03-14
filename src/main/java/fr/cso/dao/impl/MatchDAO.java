package fr.cso.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import fr.cso.dao.IMatchDAO;
import fr.cso.model.Match;

public class MatchDAO extends HibernateDaoSupport implements IMatchDAO {
	
	@Override
	public List<Match> listeMatchs() {
		return getHibernateTemplate().getSessionFactory().getCurrentSession().getNamedQuery("Match.findAll").list();
	}

	@Override
	public List<Match> listeMatchsParGroupe(String codeGroupe) {
		return getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("FROM Match m WHERE m.groupe = '" + codeGroupe + "'").list();
	}
}
