package org.agaphetos.api.tms.service;

import java.util.List;

import org.agaphetos.api.tms.entity.Task;

public interface TaskService {
	void create(Task entity);
	void update(Task entity);
	void delete(int id);
	List<Task> getList();
	List<Task> getListByEmployee(int id);
	Task findById(int id);
}
