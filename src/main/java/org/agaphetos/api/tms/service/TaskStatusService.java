package org.agaphetos.api.tms.service;

import java.util.List;

import org.agaphetos.api.tms.entity.TaskStatus;

public interface TaskStatusService {
	void create(TaskStatus entity);
	void update(TaskStatus entity);
	void delete(int id);
	List<TaskStatus> getList();
	TaskStatus findById(int id);
}
