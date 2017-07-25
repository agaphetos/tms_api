package org.agaphetos.api.tms.service;

import java.util.List;

import org.agaphetos.api.tms.entity.Employee;

public interface EmployeeService {
	void create(Employee entity);
	void update(Employee entity);
	void delete(int id);
	List<Employee> getList();
	List<Employee> getSupervisorList();
	Employee findById(int id);
	Employee findByName(String s);
	Employee findByEmail(String s);
}
