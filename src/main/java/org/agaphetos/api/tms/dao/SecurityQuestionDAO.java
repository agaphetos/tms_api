package org.agaphetos.api.tms.dao;

import java.util.List;

import org.agaphetos.api.tms.entity.SecurityQuestion;

public interface SecurityQuestionDAO {
	public void create(SecurityQuestion entity);
	public void update(SecurityQuestion entity);
	public void delete(int id);
	public List<SecurityQuestion> getList();
}
