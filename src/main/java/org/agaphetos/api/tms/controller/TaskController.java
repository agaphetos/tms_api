package org.agaphetos.api.tms.controller;

import java.util.List;

import org.agaphetos.api.tms.entity.Task;
import org.agaphetos.api.tms.service.TaskService;
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
public class TaskController {

	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	private TaskService service;
	
	@RequestMapping(value = "/Task/", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getTasks() {
		logger.info("Fetching Tasks");
        List<Task> resultList = service.getList();
        if(resultList.isEmpty()){
            return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Task>>(resultList, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/Task/Employee/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Task>> getEmployeeTasks(@PathVariable("id") int id) {
		logger.info("Fetching Tasks by employeeId " + id);
        List<Task> resultList = service.getListByEmployee(id);
        if(resultList.isEmpty()){
            return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Task>>(resultList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/Task/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Task> getTask(@PathVariable("id") int id) {
        System.out.println("Fetching Task with id " + id);
        Task entity = service.findById(id);
        if (entity == null) {
            logger.info("Task with id " + id + " not found");
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Task>(entity, HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/Task/", method = RequestMethod.POST)
    public ResponseEntity<Void> createTask(@RequestBody Task entity, UriComponentsBuilder ucBuilder) {
        service.create(entity);
        System.out.println("Creating Task " + entity.getTaskId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/Task/{id}").buildAndExpand(entity.getTaskId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/Task/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Task> updateTask(@PathVariable("id") int id, @RequestBody Task entity) {
     	System.out.println("Updating Task " + id);
     
     	Task currentRecord = service.findById(id);
         
        if (currentRecord==null) {
            System.out.println("Task with id " + id + " not found");
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
         
        service.update(entity);
        currentRecord = service.findById(id);
        logger.info("current record: " + currentRecord);
        System.out.println("Team  with id " + id + "found");
        return new ResponseEntity<Task>(currentRecord, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/Task/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Task> deleteTask(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting Task with id " + id);

        Task record = service.findById(id);
        if (record == null) {
            System.out.println("Unable to delete. Task with id " + id + " not found");
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
 
        service.delete(id);
        return new ResponseEntity<Task>(HttpStatus.NO_CONTENT);
    }
}
