package org.agaphetos.api.tms.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.agaphetos.api.tms.dao.AbstractDAO;
import org.agaphetos.api.tms.dao.SecurityQuestionDAO;
import org.agaphetos.api.tms.entity.SecurityQuestion;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository("securityQuestionDAO")
public class SecurityQuestionDAOImpl extends AbstractDAO<Integer, SecurityQuestion> implements SecurityQuestionDAO {
	private static final Logger logger = Logger.getLogger(SecurityQuestionDAOImpl.class);
	
	@Override
	public void create(SecurityQuestion entity) {
		getSession().save(entity);
		logger.info("SecurityQuestion saved successfully, SecurityQuestion Details="+entity);
	}


	@Override
	public void update(SecurityQuestion entity) {
		getSession().update(entity);
		logger.info("SecurityQuestion updated successfully, SecurityQuestion Details="+entity);
	}
	
	@Override
	public void delete(int id) {
		SecurityQuestion entity = (SecurityQuestion) getSession().load(SecurityQuestion.class, id);
		if(null != entity){
			getSession().delete(entity);
		}
		logger.info("SecurityQuestion deleted successfully, SecurityQuestion details="+entity);
	}

	@Override
	public List<SecurityQuestion> getList() {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<SecurityQuestion>  cq = cb.createQuery(SecurityQuestion.class);
        Root<SecurityQuestion> root = cq.from(SecurityQuestion.class);        
        cq.select(root);        
        return getSession().createQuery(cq).getResultList();
	}

}
