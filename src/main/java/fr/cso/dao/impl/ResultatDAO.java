package fr.cso.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import fr.cso.dao.IResultatDAO;
import fr.cso.models.Resultat;

public class ResultatDAO extends HibernateDaoSupport implements IResultatDAO {
	
	@Override
	public List<Resultat> listeResultats() {
		return getHibernateTemplate().getSessionFactory().getCurrentSession().getNamedQuery("Resultat.findAll").list();
	}
	
}
