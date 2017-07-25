package org.agaphetos.api.tms.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.agaphetos.api.tms.dao.AbstractDAO;
import org.agaphetos.api.tms.dao.TaskDAO;
import org.agaphetos.api.tms.entity.Task;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository("taskDAO")
public class TaskDAOImpl extends AbstractDAO<Integer, Task> implements TaskDAO {
	private static final Logger logger = Logger.getLogger(TaskDAOImpl.class);

	@Override
	public void create(Task entity) {
		getSession().persist(entity);
		logger.info("Task saved successfully, task Details="+entity);
	}

	@Override
	public void update(Task entity) {
		getSession().update(entity);
		logger.info("Task updated successfully");
	}
	
	@Override
	public void delete(int id) {
		Task entity = (Task) getSession().load(Task.class, id);
		if(null != entity){
			getSession().delete(entity);
		}
		logger.info("Task deleted successfully, Task details="+entity);
	}

	@Override
	public List<Task> getList() {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Task>  cq = cb.createQuery(Task.class);
        Root<Task> root = cq.from(Task.class);        
        cq.select(root);        
        return getSession().createQuery(cq).getResultList();
	}

	@Override
	public List<Task> getListByEmployee(int id) {
		CriteriaBuilder cb = createCriteriaBuilder();
		CriteriaQuery<Task> cq = cb.createQuery(Task.class);
		Root<Task> root = cq.from(Task.class); 
		cq.select(root);
		cq.where(cb.equal(root.get("employeeId"), id));
		List<Task> list = getSession().createQuery(cq).getResultList();
		if (list.size() != 0) {
			return list;
		}
		return null;
	}

	@Override
	public Task findById(int id) {
		CriteriaBuilder cb = createCriteriaBuilder();
		CriteriaQuery<Task> cq = cb.createQuery(Task.class);
		Root<Task> root = cq.from(Task.class); 
		cq.select(root);
		cq.where(cb.equal(root.get("taskId"), id));
		List<Task> list = getSession().createQuery(cq).getResultList();
		if (list.size() != 0) {
			return list.get(0);
		}
		return null;
	}
}
