package org.agaphetos.api.tms.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.agaphetos.api.tms.dao.AbstractDAO;
import org.agaphetos.api.tms.dao.ApplicationTypeDAO;
import org.agaphetos.api.tms.entity.ApplicationType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository("applicationTypeDAO")
public class ApplicationTypeDAOImpl extends AbstractDAO<Integer, ApplicationType> implements ApplicationTypeDAO {
	private static final Logger logger = Logger.getLogger(ApplicationTypeDAOImpl.class);

	@Override
	public void create(ApplicationType entity) {
		getSession().save(entity);
		logger.info("ApplicationType saved successfully, ApplicationType Details="+entity);
	}

	@Override
	public void update(ApplicationType entity) {
		getSession().update(entity);
		logger.info("ApplicationType updated successfully");
	}

	@Override
	public void delete(int id) {
		ApplicationType entity = (ApplicationType) getSession().load(ApplicationType.class, id);
		if(null != entity){
			getSession().delete(entity);
		}
		logger.info("ApplicationType deleted successfully, ApplicationType details="+entity);
	}

	@Override
	public List<ApplicationType> getList() {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<ApplicationType>  cq = cb.createQuery(ApplicationType.class);
        Root<ApplicationType> root = cq.from(ApplicationType.class);        
        cq.select(root);        
        return getSession().createQuery(cq).getResultList();
	}

	@Override
	public ApplicationType findById(int id) {
		CriteriaBuilder cb = createCriteriaBuilder();
		CriteriaQuery<ApplicationType> cq = cb.createQuery(ApplicationType.class);
		Root<ApplicationType> root = cq.from(ApplicationType.class); 
		cq.select(root);
		cq.where(cb.equal(root.get("applicationTypeId"), id));
		List<ApplicationType> list = getSession().createQuery(cq).getResultList();
		if (list.size() != 0) {
			return list.get(0);
		}
		return null;
	}
}
