package org.agaphetos.api.tms.service.impl;

import java.util.List;

import org.agaphetos.api.tms.dao.ApplicationTypeDAO;
import org.agaphetos.api.tms.entity.ApplicationType;
import org.agaphetos.api.tms.service.ApplicationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("applicationTypeService")
public class ApplicationTypeServiceImpl implements ApplicationTypeService {
	
	@Autowired
	private ApplicationTypeDAO applicationTypeDAO;
	
	public void setTaskDAO(ApplicationTypeDAO applicationTypeDAO) {
		this.applicationTypeDAO = applicationTypeDAO;
	}

	@Override
	@Transactional
	public void create(ApplicationType entity) {
		this.applicationTypeDAO.create(entity);
	}

	@Override
	@Transactional
	public void update(ApplicationType entity) {
		this.applicationTypeDAO.update(entity);
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.applicationTypeDAO.delete(id);
	}

	@Override
	@Transactional
	public List<ApplicationType> getList() {
		return this.applicationTypeDAO.getList();
	}

	@Override
	@Transactional
	public ApplicationType findById(int id) {
		return this.applicationTypeDAO.findById(id);
	}
}
