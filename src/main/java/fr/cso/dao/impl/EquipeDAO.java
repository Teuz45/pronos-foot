package fr.cso.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import fr.cso.dao.IEquipeDAO;
import fr.cso.model.Equipe;

public class EquipeDAO extends HibernateDaoSupport implements IEquipeDAO {
	
	@Override
	public List<Equipe> listeEquipes() {
		return getHibernateTemplate().getSessionFactory().getCurrentSession().getNamedQuery("Equipe.findAll").list();
	}
	
}
