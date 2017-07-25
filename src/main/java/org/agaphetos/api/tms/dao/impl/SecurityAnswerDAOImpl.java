package org.agaphetos.api.tms.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.agaphetos.api.tms.dao.AbstractDAO;
import org.agaphetos.api.tms.dao.SecurityAnswerDAO;
import org.agaphetos.api.tms.entity.Employee;
import org.agaphetos.api.tms.entity.SecurityAnswer;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository("securityAnswerDAO")
public class SecurityAnswerDAOImpl extends AbstractDAO<Integer, SecurityAnswer> implements SecurityAnswerDAO {
	private static final Logger logger = Logger.getLogger(SecurityAnswerDAOImpl.class);

	@Override
	public void create(SecurityAnswer entity) {
		getSession().save(entity);
		logger.info("SecurityAnswer saved successfully, SecurityAnswer Details="+entity);
	}
	
	@Override
	public void update(SecurityAnswer entity) {
		getSession().update(entity);
		logger.info("SecurityAnswer updated successfully, SecurityAnswer Details="+entity);
	}

	@Override
	public List<SecurityAnswer> findByEmployee(Employee employee) {		
		CriteriaBuilder cb = createCriteriaBuilder();
		CriteriaQuery<SecurityAnswer> cq = cb.createQuery(SecurityAnswer.class);
		Root<SecurityAnswer> root = cq.from(SecurityAnswer.class); 
		cq.where(cb.equal(root.get("employee"), employee)).orderBy(cb.asc(root.get("questionOrder")));
		
		List<SecurityAnswer> resultList = getSession().createQuery(cq).getResultList();
		if (resultList.size() != 0) {
			return resultList;
		}
		return null;
	}
	

}
