package org.agaphetos.api.tms.service.impl;

import java.util.List;

import org.agaphetos.api.tms.dao.TaskTypeDAO;
import org.agaphetos.api.tms.entity.TaskType;
import org.agaphetos.api.tms.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("taskTypeService")
public class TaskTypeServiceImpl implements TaskTypeService {

	@Autowired 
	private TaskTypeDAO taskTypeDAO;
	
	public void setTaskTypeValuesDAO(TaskTypeDAO taskTypeDAO) {
		this.taskTypeDAO = taskTypeDAO;
	}

	@Override
	@Transactional
	public void create(TaskType entity) {
		this.taskTypeDAO.create(entity);
	}

	@Override
	@Transactional
	public void update(TaskType entity) {
		this.taskTypeDAO.update(entity);
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.taskTypeDAO.delete(id);
	}

	@Override
	@Transactional
	public List<TaskType> getList() {
		return this.taskTypeDAO.getList();
	}

	@Override
	@Transactional
	public TaskType findById(int id) {
		return this.taskTypeDAO.findById(id);
	}
}
