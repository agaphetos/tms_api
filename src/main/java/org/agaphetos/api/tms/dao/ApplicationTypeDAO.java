package org.agaphetos.api.tms.dao;

import java.util.List;

import org.agaphetos.api.tms.entity.ApplicationType;

public interface ApplicationTypeDAO {
	public void create(ApplicationType entity);
	public void update(ApplicationType entity);
	public void delete(int id);
	public List<ApplicationType> getList();
	public ApplicationType findById(int id);
}
