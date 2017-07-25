package org.agaphetos.api.tms.dao;

import java.util.List;

import org.agaphetos.api.tms.entity.Token;

public interface TokenDAO {
	public void create(Token entity);
	public void delete(String s);
	public List<Token> getList();
	public Token findBySeries(String s);
}
