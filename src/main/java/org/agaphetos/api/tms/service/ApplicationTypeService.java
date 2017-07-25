package org.agaphetos.api.tms.service;

import java.util.List;

import org.agaphetos.api.tms.entity.ApplicationType;

public interface ApplicationTypeService {
	void create(ApplicationType entity);
	void update(ApplicationType entity);
	void delete(int id);
	List<ApplicationType> getList();
	ApplicationType findById(int id);
}
