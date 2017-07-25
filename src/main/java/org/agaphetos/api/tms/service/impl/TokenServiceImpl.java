package org.agaphetos.api.tms.service.impl;

import java.util.List;

import org.agaphetos.api.tms.dao.TokenDAO;
import org.agaphetos.api.tms.entity.Token;
import org.agaphetos.api.tms.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tokenService")
public class TokenServiceImpl implements TokenService{

	@Autowired
	private TokenDAO tokenDAO;
	
	public void setTokenDAO(TokenDAO tokenDAO) {
		this.tokenDAO = tokenDAO;
	}
	
	@Override
	@Transactional
	public void create(Token t) {
		this.tokenDAO.create(t);
	}

	@Override
	@Transactional
	public void delete(String s) {
		this.tokenDAO.delete(s);
	}

	@Override
	@Transactional
	public Token findBySeries(String s) {
		return this.tokenDAO.findBySeries(s);
	}

	@Override
	@Transactional
	public List<Token> getList() {
		return this.tokenDAO.getList();
	}

}
