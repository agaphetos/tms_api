package org.agaphetos.api.tms.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.agaphetos.api.tms.dao.AbstractDAO;
import org.agaphetos.api.tms.dao.UserRoleDAO;
import org.agaphetos.api.tms.entity.UserRole;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository("userRoleDAO")
public class UserRoleDAOImpl extends AbstractDAO<Integer, UserRole> implements UserRoleDAO {
	private static final Logger logger = Logger.getLogger(UserRoleDAOImpl.class);

	@Override
	public void create(UserRole entity) {
		getSession().save(entity);
		logger.info("UserRole saved successfully, UserRole Details="+entity);
	}

	@Override
	public void update(UserRole entity) {
		getSession().update(entity);
		logger.info("UserRole updated successfully, UserRole Details="+entity);
	}

	@Override
	public void delete(int id) {
		UserRole entity = (UserRole) getSession().load(UserRole.class, id);
		if(null != entity){
			getSession().delete(entity);
		}
		logger.info("UserRole deleted successfully, UserRole details="+entity);
	}

	@Override
	public List<UserRole> getList() {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<UserRole>  cq = cb.createQuery(UserRole.class);
        Root<UserRole> root = cq.from(UserRole.class);        
        cq.select(root);        
        return getSession().createQuery(cq).getResultList();
	}

	@Override
	public UserRole findById(int id) {
		CriteriaBuilder cb = createCriteriaBuilder();
		CriteriaQuery<UserRole> cq = cb.createQuery(UserRole.class);
		Root<UserRole> root = cq.from(UserRole.class); 
		cq.select(root);
		cq.where(cb.equal(root.get("roleId"), id));
		List<UserRole> list = getSession().createQuery(cq).getResultList();
		if (list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public UserRole findByDescription(String s) {
		CriteriaBuilder cb = createCriteriaBuilder();
		CriteriaQuery<UserRole> cq = cb.createQuery(UserRole.class);
		Root<UserRole> root = cq.from(UserRole.class); 
		cq.select(root);
		cq.where(cb.equal(root.get("description"), s));
		List<UserRole> list = getSession().createQuery(cq).getResultList();
		if (list.size() != 0) {
			return list.get(0);
		}
		return null;
	}
}
