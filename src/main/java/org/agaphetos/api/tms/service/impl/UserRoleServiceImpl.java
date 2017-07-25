package org.agaphetos.api.tms.service.impl;

import java.util.List;

import org.agaphetos.api.tms.dao.UserRoleDAO;
import org.agaphetos.api.tms.entity.UserRole;
import org.agaphetos.api.tms.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleDAO userRoleDAO;
	
	public void setUserRoleDAO(UserRoleDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}
	
	@Override
	@Transactional
	public void create(UserRole entity) {
		this.userRoleDAO.create(entity);
	}

	@Override
	@Transactional
	public void update(UserRole entity) {
		this.userRoleDAO.update(entity);
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.userRoleDAO.delete(id);
	}

	@Override
	@Transactional
	public List<UserRole> getList() {
		return this.userRoleDAO.getList();
	}

	@Override
	@Transactional
	public UserRole findById(int id) {
		return this.userRoleDAO.findById(id);
	}

	@Override
	@Transactional
	public UserRole findByDescription(String s) {
		return this.userRoleDAO.findByDescription(s);
	}

}
