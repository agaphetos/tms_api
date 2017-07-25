package org.agaphetos.api.tms.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.agaphetos.api.tms.dao.AbstractDAO;
import org.agaphetos.api.tms.dao.TokenDAO;
import org.agaphetos.api.tms.entity.Token;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository("tokenDAO")
public class TokenDAOImpl extends AbstractDAO<Integer, Token> implements TokenDAO {
	private static final Logger logger = Logger.getLogger(TokenDAOImpl.class);
		
	@Override
	public void create(Token entity) {
		getSession().persist(entity);
		logger.info("Token saved successfully, Token Details="+entity);
	}
	
	@Override
	public void delete(String s) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
	    CriteriaQuery<Token>  cq = cb.createQuery(Token.class);
	    Root<Token> root = cq.from(Token.class);	    
	    cq.select(root);
	    cq.where(cb.equal(root.get("series"), s)); 
	    
	    Token entity = null;
	    List<Token> list = getSession().createQuery(cq).getResultList();
	    if(list.size() > 0){
	    	entity = list.get(0);
	    }
	    
		if(null != entity){
			getSession().delete(entity);
		}
		logger.info("Token deleted successfully, Token details="+entity);
	}
	
	@Override
    public List<Token> getList() {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Token>  cq = cb.createQuery(Token.class);
        Root<Token> root = cq.from(Token.class); 
        cq.select(root);
        return getSession().createQuery(cq).getResultList();
    }
	
	@Override
    public Token findBySeries(String s) {
	    CriteriaBuilder cb = getSession().getCriteriaBuilder();
	    CriteriaQuery<Token>  cq = cb.createQuery(Token.class);
	    Root<Token> root = cq.from(Token.class); 
	    
	    cq.select(root);
	    cq.where(cb.equal(root.get("series"), s)); 
	    
	    Token entity = null;
	    List<Token> list = getSession().createQuery(cq).getResultList();
	    if(list.size() > 0){
	    	entity = list.get(0);
	    }
	    
	    return entity;
    }
}
