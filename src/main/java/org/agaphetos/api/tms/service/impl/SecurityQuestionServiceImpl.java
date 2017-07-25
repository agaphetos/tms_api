package org.agaphetos.api.tms.service.impl;

import java.util.List;

import org.agaphetos.api.tms.dao.SecurityQuestionDAO;
import org.agaphetos.api.tms.entity.SecurityQuestion;
import org.agaphetos.api.tms.service.SecurityQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("securityQuestionService")
public class SecurityQuestionServiceImpl implements SecurityQuestionService {

	@Autowired
	private SecurityQuestionDAO securityQuestionDAO;
	  
	public void setSecurityQuestionDAO(SecurityQuestionDAO securityQuestionDAO) {
		this.securityQuestionDAO = securityQuestionDAO;
	}

	@Override
	@Transactional
	public void create(SecurityQuestion entity) {
		this.securityQuestionDAO.create(entity);
	}

	@Override
	@Transactional
	public void update(SecurityQuestion entity) {
		this.securityQuestionDAO.update(entity);
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.securityQuestionDAO.delete(id);
	}

	@Override
	@Transactional
	public List<SecurityQuestion> getList() {
		return this.securityQuestionDAO.getList();
	}

}
