package org.agaphetos.api.tms.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.agaphetos.api.tms.dao.AbstractDAO;
import org.agaphetos.api.tms.dao.EmployeeDAO;
import org.agaphetos.api.tms.entity.Employee;
import org.agaphetos.api.tms.entity.UserRole;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository("employeeDAO")
public class EmployeeDAOImpl extends AbstractDAO<Integer, Employee> implements EmployeeDAO {
	private static final Logger logger = Logger.getLogger(EmployeeDAOImpl.class);
		
	@Override
	public void create(Employee entity) {
		getSession().save(entity);
		logger.info("Employee saved successfully, Employee Details="+entity);
	}

	@Override
	public void update(Employee entity) {
		getSession().update(entity);
		logger.info("Employee updated successfully");
	}

	@Override
	public void delete(int id) {
		Employee entity = (Employee) getSession().load(Employee.class, id);
		if(null != entity){
			getSession().delete(entity);
		}
		logger.info("Employee deleted successfully, Employee details="+entity);
	}
	
	@Override
	public List<Employee> getList() {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Employee>  cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);        
        cq.select(root);        
        return getSession().createQuery(cq).getResultList();
	}

	@Override
	public List<Employee> getSupervisorList() {
		CriteriaBuilder cbRole = createCriteriaBuilder();
		CriteriaQuery<UserRole> cqRole = cbRole.createQuery(UserRole.class);
		Root<UserRole> rootRole = cqRole.from(UserRole.class);
		cqRole.where(cbRole.equal(rootRole.get("isSupervisor"), 1));
		List<UserRole> supervisorRoleList = getSession().createQuery(cqRole).getResultList();
		
		CriteriaBuilder cb = createCriteriaBuilder();
		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
		Root<Employee> root = cq.from(Employee.class); 
		cq.where(root.get("userRole").in(supervisorRoleList));
		
		List<Employee> resultList = getSession().createQuery(cq).getResultList();
		if (resultList.size() != 0) {
			return resultList;
		}
		return null;
	}

	@Override
	public Employee findById(int id) {
		CriteriaBuilder cb = createCriteriaBuilder();
		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
		Root<Employee> root = cq.from(Employee.class); 
		cq.select(root);
		cq.where(cb.equal(root.get("employeeId"), id));
		List<Employee> list = getSession().createQuery(cq).getResultList();
		if (list.size() != 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public Employee findByName(String s) {
		Employee e = (Employee) getSession().load(Employee.class, new String(s));
		logger.info("Person loaded successfully, Person details="+e);
		return e;
	}
	
	@Override
	public Employee findByEmail(String s) {
		CriteriaBuilder cb = createCriteriaBuilder();
		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
		Root<Employee> root = cq.from(Employee.class); 
		cq.select(root);
		cq.where(cb.equal(root.get("emailAddress"), s));
		List<Employee> list = getSession().createQuery(cq).getResultList();
		if (list.size() != 0) {
			return list.get(0);
		}
		return null;
	}
}
