package org.agaphetos.api.tms.service.impl;

import java.util.List;

import org.agaphetos.api.tms.dao.TaskDAO;
import org.agaphetos.api.tms.entity.Task;
import org.agaphetos.api.tms.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("taskService")
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskDAO taskDAO;
	
	public void setTaskDAO(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}

	@Override
	@Transactional
	public void create(Task entity) {
		this.taskDAO.create(entity);
	}

	@Override
	@Transactional
	public void update(Task entity) {
		this.taskDAO.update(entity);
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.taskDAO.delete(id);
	}

	@Override
	@Transactional
	public List<Task> getList() {
		return this.taskDAO.getList();
	}
	
	@Override
	@Transactional
	public List<Task> getListByEmployee(int id) {
		return this.taskDAO.getListByEmployee(id);
	}

	@Override
	@Transactional
	public Task findById(int id) {
		return this.taskDAO.findById(id);
	}
}
