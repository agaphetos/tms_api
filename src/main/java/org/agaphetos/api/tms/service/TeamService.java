package org.agaphetos.api.tms.service;

import java.util.List;

import org.agaphetos.api.tms.entity.Employee;
import org.agaphetos.api.tms.entity.Team;

public interface TeamService {
	void create(Team entity);
	void update(Team entity);
	void delete(int id);
	List<Team> getList();
	Team findById(int id);
	Team findByDescription(String s);
	List<Employee> getMembers(int id);
}
