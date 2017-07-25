package org.agaphetos.api.tms.controller;

import java.util.ArrayList;
import java.util.List;

import org.agaphetos.api.tms.entity.Employee;
import org.agaphetos.api.tms.entity.Team;
import org.agaphetos.api.tms.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class TeamController {

	private static final Logger logger = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    private TeamService service;
    
    @RequestMapping(value = "/Team/", method = RequestMethod.GET)
    public ResponseEntity<List<Team>> getTeams() {
    	System.out.println("Fetching Teams");
        List<Team> resultList = service.getList();
        if(resultList.isEmpty()){
            return new ResponseEntity<List<Team>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Team>>(resultList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/Team/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Employee>> getTeam(@PathVariable("id") int id) {
        System.out.println("Fetching Team with id " + id);
        Team entity = service.findById(id);
        List<Employee> members = new ArrayList<Employee>();
        if (entity == null) {
            logger.info("Team with id " + id + " not found");
            return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
        } else {
        	members = service.getMembers(id);
        }
        
        if (members == null) {
        	return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
        }
        
        if (members.size() != 0) {
        	for (Employee e : members) {
        		logger.info(e.toString());
        	}
        	return new ResponseEntity<List<Employee>>(members, HttpStatus.OK);
        }
        	
        return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/Team/", method = RequestMethod.POST)
    public ResponseEntity<Void> createTeam(@RequestBody Team entity, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Team " + entity.getDescription());
 
        if (service.findByDescription(entity.getDescription()) != null) {
            System.out.println("A Team with name " + entity.getDescription() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        service.create(entity);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/Team/{id}").buildAndExpand(entity.getTeamId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/Team/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Team> updateTeam(@PathVariable("id") int id, @RequestBody Team entity) {
     	System.out.println("Updating Team " + id);
     
     	Team currentRecord = service.findById(id);
     	
        if (currentRecord==null) {
            System.out.println("Team with id " + id + " not found");
            return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);
        }
        
        entity.setMembers(currentRecord.getMembers());
        service.update(entity);
        currentRecord = service.findById(id);
        logger.info("current record: " + currentRecord);
        System.out.println("Team  with id " + id + "found");
        return new ResponseEntity<Team>(currentRecord, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/Team/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Team> deleteTeam(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting Team with id " + id);

        Team record = service.findById(id);
        if (record == null) {
            System.out.println("Unable to delete. Team with id " + id + " not found");
            return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);
        }
 
        service.delete(id);
        return new ResponseEntity<Team>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/Team/Members/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Team> updateTeamMembers(@PathVariable("id") int id, @RequestBody List<Employee> members) {
     	System.out.println("Updating Team Members" + id);
     
     	Team currentRecord = service.findById(id);
         
        if (currentRecord==null) {
            System.out.println("Team with id " + id + " not found");
            return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);
        } else {
        	currentRecord.setMembers(members);
	        service.update(currentRecord);
	        currentRecord = service.findById(id);
	        logger.info("current record: " + currentRecord);
	        System.out.println("Team  with id " + id + "found");
	        return new ResponseEntity<Team>(currentRecord, HttpStatus.OK);
        }
    }
}
