package org.agaphetos.api.tms.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.agaphetos.api.tms.dao.AbstractDAO;
import org.agaphetos.api.tms.dao.TeamDAO;
import org.agaphetos.api.tms.entity.Employee;
import org.agaphetos.api.tms.entity.Team;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository("teamDAO")
public class TeamDAOImpl extends AbstractDAO<Integer, Team> implements TeamDAO {
	private static final Logger logger = Logger.getLogger(TeamDAOImpl.class);
	
	@Override
	public void create(Team entity) {
		getSession().save(entity);
		logger.info("Team saved successfully, Team Details="+entity);
	}
	
	@Override
	public void update(Team entity) {
		getSession().update(entity);
		logger.info("Team updated successfully, Team Details="+entity);
	}

	@Override
	public void delete(int id) {
		Team entity = (Team) getSession().load(Team.class, id);
		if(null != entity){
			getSession().delete(entity);
		}
		logger.info("Team deleted successfully, Team details="+entity);
	}

	@Override
	public List<Team> getList() {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Team>  cq = cb.createQuery(Team.class);
        Root<Team> root = cq.from(Team.class);        
        cq.select(root);        
        return getSession().createQuery(cq).getResultList();
	}

	@Override
	public Team findById(int id) {
		CriteriaBuilder cb = createCriteriaBuilder();
		CriteriaQuery<Team> cq = cb.createQuery(Team.class);
		Root<Team> root = cq.from(Team.class); 
		cq.select(root);
		cq.where(cb.equal(root.get("teamId"), id));
		List<Team> list = getSession().createQuery(cq).getResultList();
		if (list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Team findByDescription(String s) {
		CriteriaBuilder cb = createCriteriaBuilder();
		CriteriaQuery<Team> cq = cb.createQuery(Team.class);
		Root<Team> root = cq.from(Team.class); 
		cq.select(root);
		cq.where(cb.equal(root.get("description"), s));
		List<Team> list = getSession().createQuery(cq).getResultList();
		if (list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Employee> getMembers(int id) {
		CriteriaBuilder cb = createCriteriaBuilder();
		CriteriaQuery<Team> cq = cb.createQuery(Team.class);
		Root<Team> root = cq.from(Team.class); 
		cq.select(root);
		cq.where(cb.equal(root.get("teamId"), id));
		
		Team team = getSession().createQuery(cq).getSingleResult();
		List<Employee> members = team.getMembers();
		if (members.size() != 0) {
			return members;
		}
		return null;
	}
}
