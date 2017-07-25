package org.agaphetos.api.tms.dao;

import java.util.List;

import org.agaphetos.api.tms.entity.TaskType;

public interface TaskTypeDAO {
	public void create(TaskType entity);
	public void update(TaskType entity);
	public void delete(int id);
	public List<TaskType> getList();
	public TaskType findById(int id);
}
