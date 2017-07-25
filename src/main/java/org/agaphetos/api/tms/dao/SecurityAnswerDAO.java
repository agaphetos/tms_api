package org.agaphetos.api.tms.dao;

import java.util.List;

import org.agaphetos.api.tms.entity.Employee;
import org.agaphetos.api.tms.entity.SecurityAnswer;

public interface SecurityAnswerDAO {
	public void create(SecurityAnswer entity);
	public void update(SecurityAnswer entity);
	public List<SecurityAnswer> findByEmployee(Employee employee);
}
