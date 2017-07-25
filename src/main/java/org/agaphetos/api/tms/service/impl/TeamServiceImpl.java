package org.agaphetos.api.tms.service.impl;

import java.util.List;

import org.agaphetos.api.tms.dao.TeamDAO;
import org.agaphetos.api.tms.entity.Employee;
import org.agaphetos.api.tms.entity.Team;
import org.agaphetos.api.tms.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("teamService")
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamDAO teamDAO;
	
	public void setTeamDAO(TeamDAO teamDAO) {
		this.teamDAO = teamDAO;
	}
	
	@Override
	@Transactional
	public void create(Team entity) {
		this.teamDAO.create(entity);
	}

	@Override
	@Transactional
	public void update(Team entity) {
		this.teamDAO.update(entity);
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.teamDAO.delete(id);
	}

	@Override
	@Transactional
	public List<Team> getList() {
		return this.teamDAO.getList();
	}

	@Override
	@Transactional
	public Team findById(int id) {
		return this.teamDAO.findById(id);
	}

	@Override
	@Transactional
	public Team findByDescription(String s) {
		return this.teamDAO.findByDescription(s);
	}

	@Override
	@Transactional
	public List<Employee> getMembers(int id) {
		return this.teamDAO.getMembers(id);
	}
}
