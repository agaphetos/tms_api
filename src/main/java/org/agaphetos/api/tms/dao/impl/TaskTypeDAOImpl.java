package org.agaphetos.api.tms.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.agaphetos.api.tms.dao.AbstractDAO;
import org.agaphetos.api.tms.dao.TaskTypeDAO;
import org.agaphetos.api.tms.entity.TaskType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository("taskTypeValuesDAO")
public class TaskTypeDAOImpl extends AbstractDAO<Integer, TaskType> implements TaskTypeDAO {
	private static final Logger logger = Logger.getLogger(TaskTypeDAOImpl.class);

	@Override
	public void create(TaskType entity) {
		getSession().save(entity);
		logger.info("TaskTypeValues saved successfully, TaskTypeValues Details="+entity);
	}

	@Override
	public void update(TaskType entity) {
		getSession().update(entity);
		logger.info("TaskTypeValues updated successfully");
	}

	@Override
	public void delete(int id) {
		TaskType entity = (TaskType) getSession().load(TaskType.class, id);
		if(null != entity){
			getSession().delete(entity);
		}
		logger.info("TaskTypeValues deleted successfully, TaskTypeValues details="+entity);
	}

	@Override
	public List<TaskType> getList() {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<TaskType>  cq = cb.createQuery(TaskType.class);
        Root<TaskType> root = cq.from(TaskType.class);        
        cq.select(root);        
        return getSession().createQuery(cq).getResultList();
	}

	@Override
	public TaskType findById(int id) {
		CriteriaBuilder cb = createCriteriaBuilder();
		CriteriaQuery<TaskType> cq = cb.createQuery(TaskType.class);
		Root<TaskType> root = cq.from(TaskType.class); 
		cq.select(root);
		cq.where(cb.equal(root.get("taskTypeId"), id));
		List<TaskType> list = getSession().createQuery(cq).getResultList();
		if (list.size() != 0) {
			return list.get(0);
		}
		return null;
	}
}
