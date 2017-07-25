package org.agaphetos.api.tms.service;

import java.util.List;

import org.agaphetos.api.tms.entity.Employee;
import org.agaphetos.api.tms.entity.SecurityAnswer;

public interface SecurityAnswerService {
	void create(SecurityAnswer entity);
	void update(SecurityAnswer entity);
	List<SecurityAnswer> findByEmployee(Employee employee);
}
