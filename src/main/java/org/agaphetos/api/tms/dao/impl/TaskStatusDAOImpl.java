package org.agaphetos.api.tms.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.agaphetos.api.tms.dao.AbstractDAO;
import org.agaphetos.api.tms.dao.TaskStatusDAO;
import org.agaphetos.api.tms.entity.TaskStatus;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository("statusValuesDAO")
public class TaskStatusDAOImpl extends AbstractDAO<Integer, TaskStatus> implements TaskStatusDAO {
	private static final Logger logger = Logger.getLogger(TaskStatusDAOImpl.class);

	@Override
	public void create(TaskStatus entity) {
		getSession().save(entity);
		logger.info("StatusValues saved successfully, StatusValues Details="+entity);
	}

	@Override
	public void update(TaskStatus entity) {
		getSession().update(entity);
		logger.info("StatusValues updated successfully");
	}

	@Override
	public void delete(int id) {
		TaskStatus entity = (TaskStatus) getSession().load(TaskStatus.class, id);
		if(null != entity){
			getSession().delete(entity);
		}
		logger.info("StatusValues deleted successfully, StatusValues details="+entity);
	}

	@Override
	public List<TaskStatus> getList() {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<TaskStatus>  cq = cb.createQuery(TaskStatus.class);
        Root<TaskStatus> root = cq.from(TaskStatus.class);        
        cq.select(root);        
        return getSession().createQuery(cq).getResultList();
	}

	@Override
	public TaskStatus findById(int id) {
		CriteriaBuilder cb = createCriteriaBuilder();
		CriteriaQuery<TaskStatus> cq = cb.createQuery(TaskStatus.class);
		Root<TaskStatus> root = cq.from(TaskStatus.class); 
		cq.select(root);
		cq.where(cb.equal(root.get("statusId"), id));
		List<TaskStatus> list = getSession().createQuery(cq).getResultList();
		if (list.size() != 0) {
			return list.get(0);
		}
		return null;
	}
}
