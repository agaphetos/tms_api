package org.agaphetos.api.tms.service;

import java.util.List;

import org.agaphetos.api.tms.entity.Token;

public interface TokenService {
	void create(Token entity);
	void delete(String s);
	List<Token> getList();
	Token findBySeries(String s);
}
