package org.agaphetos.api.tms.dao;

import java.util.List;

import org.agaphetos.api.tms.entity.Task;

public interface TaskDAO {
	public void create(Task entity);
	public void update(Task entity);
	public void delete(int id);
	public List<Task> getList();
	public List<Task> getListByEmployee(int id);
	public Task findById(int id);
}
