package org.agaphetos.api.tms.service.impl;

import java.util.List;

import org.agaphetos.api.tms.dao.SecurityAnswerDAO;
import org.agaphetos.api.tms.entity.Employee;
import org.agaphetos.api.tms.entity.SecurityAnswer;
import org.agaphetos.api.tms.service.SecurityAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("securityAnswerService")
public class SecurityAnswerServiceImpl implements SecurityAnswerService {

	@Autowired
	private SecurityAnswerDAO securityAnswerDAO;
	
	public void setSecurityAnswerDAO(SecurityAnswerDAO securityAnswerDAO) {
		this.securityAnswerDAO = securityAnswerDAO;
	}
	
	@Override
	@Transactional
	public void create(SecurityAnswer entity) {
		this.securityAnswerDAO.create(entity);
	}

	@Override
	@Transactional
	public void update(SecurityAnswer entity) {
		this.securityAnswerDAO.update(entity);
	}

	@Override
	@Transactional
	public List<SecurityAnswer> findByEmployee(Employee employee) {
		return this.securityAnswerDAO.findByEmployee(employee);
	}
}
