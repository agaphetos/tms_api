package org.agaphetos.api.tms.dao;

import java.util.List;

import org.agaphetos.api.tms.entity.TaskStatus;

public interface TaskStatusDAO {
	public void create(TaskStatus entity);
	public void update(TaskStatus entity);
	public void delete(int id);
	public List<TaskStatus> getList();
	public TaskStatus findById(int id);
}
