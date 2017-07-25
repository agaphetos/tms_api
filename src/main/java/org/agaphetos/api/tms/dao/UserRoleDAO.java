package org.agaphetos.api.tms.dao;

import java.util.List;

import org.agaphetos.api.tms.entity.UserRole;

public interface UserRoleDAO {
	public void create(UserRole entity);
	public void update(UserRole entity);
	public void delete(int id);
	public List<UserRole> getList();
	public UserRole findById(int id);
	public UserRole findByDescription(String s);
}
