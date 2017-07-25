package org.agaphetos.api.tms.dao;

import java.util.List;

import org.agaphetos.api.tms.entity.Employee;

public interface EmployeeDAO {
	public void create(Employee entity);
	public void update(Employee entity);
	public void delete(int id);
	public List<Employee> getList();
	public List<Employee> getSupervisorList();
	public Employee findById(int id);
	public Employee findByName(String s);
	public Employee findByEmail(String s);
}
