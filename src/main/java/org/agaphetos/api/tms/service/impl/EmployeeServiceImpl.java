package org.agaphetos.api.tms.service.impl;

import java.util.List;

import org.agaphetos.api.tms.dao.EmployeeDAO;
import org.agaphetos.api.tms.entity.Employee;
import org.agaphetos.api.tms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeDAO employeeDAO;
	
	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	
	@Override
	@Transactional
	public void create(Employee entity) {
		this.employeeDAO.create(entity);
	}

	@Override
	@Transactional
	public void update(Employee entity) {
		this.employeeDAO.update(entity);
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.employeeDAO.delete(id);
	}

	@Override
	@Transactional
	public List<Employee> getList() {
		return this.employeeDAO.getList();
	}

	@Override
	@Transactional
	public List<Employee> getSupervisorList() {
		return this.employeeDAO.getSupervisorList();
	}

	@Override
	@Transactional
	public Employee findById(int id) {
		return employeeDAO.findById(id);
	}

	@Override
	@Transactional
	public Employee findByName(String s) {
		return this.employeeDAO.findByName(s);
	}

	@Override
	@Transactional
	public Employee findByEmail(String s) {
		return this.employeeDAO.findByEmail(s);
	}
}
