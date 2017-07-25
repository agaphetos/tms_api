package org.agaphetos.api.tms.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDAO<PK extends Serializable, T> {

	private final Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
    public AbstractDAO(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

	@Autowired
    private SessionFactory sessionFactory;
	
    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }
    
    public T getByKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }

    public void persist(T entity) {
        getSession().persist(entity);
    }
    public void update(T entity) {
        getSession().update(entity);
    }
    public void delete(T entity) {
        getSession().delete(entity);
    }
     
    protected CriteriaBuilder createCriteriaBuilder(){
        return getSession().getCriteriaBuilder();
    }
}
