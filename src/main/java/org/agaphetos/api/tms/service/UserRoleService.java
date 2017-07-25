package org.agaphetos.api.tms.service;

import java.util.List;

import org.agaphetos.api.tms.entity.UserRole;

public interface UserRoleService {
	void create(UserRole entity);
	void update(UserRole entity);
	void delete(int id);
	List<UserRole> getList();
	UserRole findById(int id);
	UserRole findByDescription(String s);
}
