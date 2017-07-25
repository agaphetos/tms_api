package org.agaphetos.api.tms.service;

import java.util.List;

import org.agaphetos.api.tms.entity.TaskType;

public interface TaskTypeService {
	void create(TaskType entity);
	void update(TaskType entity);
	void delete(int id);
	List<TaskType> getList();
	TaskType findById(int id);
}
