package org.agaphetos.api.tms.service.impl;

import java.util.List;

import org.agaphetos.api.tms.dao.TaskStatusDAO;
import org.agaphetos.api.tms.entity.TaskStatus;
import org.agaphetos.api.tms.service.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("taskStatusService")
public class TaskStatusServiceImpl implements TaskStatusService {

	@Autowired
	private TaskStatusDAO taskStatusDAO;
	
	public void setStatusValuesDAO(TaskStatusDAO taskStatusDAO) {
		this.taskStatusDAO = taskStatusDAO;
	}

	@Override
	@Transactional
	public void create(TaskStatus entity) {
		this.taskStatusDAO.create(entity);
	}

	@Override
	@Transactional
	public void update(TaskStatus entity) {
		this.taskStatusDAO.update(entity);
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.taskStatusDAO.delete(id);
	}

	@Override
	@Transactional
	public List<TaskStatus> getList() {
		return this.taskStatusDAO.getList();
	}

	@Override
	@Transactional
	public TaskStatus findById(int id) {
		return this.taskStatusDAO.findById(id);
	}
}
