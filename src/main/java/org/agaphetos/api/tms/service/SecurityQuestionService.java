package org.agaphetos.api.tms.service;

import java.util.List;

import org.agaphetos.api.tms.entity.SecurityQuestion;

public interface SecurityQuestionService {
	void create(SecurityQuestion entity);
	void update(SecurityQuestion entity);
	void delete(int id);
	List<SecurityQuestion> getList();
}
