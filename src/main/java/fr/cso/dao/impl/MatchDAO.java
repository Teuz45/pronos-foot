package fr.cso.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import fr.cso.dao.IMatchDAO;
import fr.cso.model.Match;

public class MatchDAO extends HibernateDaoSupport implements IMatchDAO {
	
	private Session getCurrentSession() {
		return getHibernateTemplate().getSessionFactory().getCurrentSession();
	}
	
	@Override
	public List<Match> listeMatchs() {
		return getCurrentSession().getNamedQuery("Match.findAll").list();
	}

	@Override
	public List<Match> listeMatchsParGroupe(String codeGroupe) {
		return getCurrentSession().createQuery("FROM Match m WHERE m.groupe = '" + codeGroupe + "'").list();
	}
	
	@Override
	public Match getProchainMatch() {
		return (Match) getCurrentSession().createQuery("FROM Match m WHERE (m.numMatch, m.date) IN (SELECT numMatch, min(mm.date) FROM Match mm WHERE mm.date > current_date)").uniqueResult();
	}
}
