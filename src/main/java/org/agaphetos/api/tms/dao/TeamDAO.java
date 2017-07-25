package org.agaphetos.api.tms.dao;

import java.util.List;

import org.agaphetos.api.tms.entity.Employee;
import org.agaphetos.api.tms.entity.Team;

public interface TeamDAO {
	public void create(Team entity);
	public void update(Team entity);
	public void delete(int id);
	public List<Team> getList();
	public Team findById(int id);
	public Team findByDescription(String s);
	public List<Employee> getMembers(int id);
}
