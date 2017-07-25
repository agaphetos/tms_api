package org.agaphetos.api.tms.controller;

import java.util.List;

import org.agaphetos.api.tms.entity.Employee;
import org.agaphetos.api.tms.service.EmployeeService;
import org.agaphetos.api.tms.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private EmployeeService userService;
    
    @Autowired 
    private TeamService teamService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @RequestMapping(value = "/User/", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getUsers() {
        List<Employee> resultList = userService.getList();
        if(resultList.isEmpty()){
            return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Employee>>(resultList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/User/Supervisors", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getSupervisors() {
        List<Employee> resultList = userService.getSupervisorList();
        if(resultList.isEmpty()){
            return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Employee>>(resultList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/User/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Employee> getUser(@PathVariable("id") String s) {
        System.out.println("Fetching User with id " + s);
        Employee employee;        
        try {
        	employee = userService.findById(Integer.parseInt(s));
            if (employee == null) {
                System.out.println("User with id " + s + " not found");
                return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
            }
        } catch (NumberFormatException nfe) {
        	employee = userService.findByEmail(s);
        	if (employee == null) {
                System.out.println("User with email " + s + " not found");
                return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/User/NotMembers/{teamId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Employee>> getNotMembers(@PathVariable("teamId") int id) {
        System.out.println("Fetching not Members from list.");
        List<Employee> employees = userService.getList();
        List<Employee> teamMembers = teamService.getMembers(id);
        if (teamMembers == null) {
        	logger.info("No Team Members.");
        } else {
        	employees.removeAll(teamMembers);
        }
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    @RequestMapping(value = "/User/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Employee entity, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + entity.getFullName());
//        logger.info(entity.toString());
        if (userService.findByEmail(entity.getEmailAddress()) != null) {
            System.out.println("A User with name " + entity.getFullName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        entity.setPassword(passwordEncoder.encode("Abcd1234"));
        userService.create(entity);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/User/{id}").buildAndExpand(entity.getEmployeeId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/User/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Employee> updateUser(@PathVariable("id") int id, @RequestBody Employee entity) {
     	System.out.println("Updating User " + id);
     
        Employee currentRecord = userService.findById(id);
         
        if (currentRecord==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        } else {
	        userService.update(entity);
	        currentRecord = userService.findById(id);
	        System.out.println("User with id " + id + "found");
	        return new ResponseEntity<Employee>(currentRecord, HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/User/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Employee> deleteUser(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting User with id " + id);

        Employee record = userService.findById(id);
        if (record == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
 
        userService.delete(id);
        return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
    }
}