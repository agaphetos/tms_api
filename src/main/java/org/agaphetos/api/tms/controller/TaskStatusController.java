package org.agaphetos.api.tms.controller;

import java.util.List;

import org.agaphetos.api.tms.entity.TaskStatus;
import org.agaphetos.api.tms.service.TaskStatusService;
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
public class TaskStatusController {

	private static final Logger logger = LoggerFactory.getLogger(TaskStatusController.class);
	
	@Autowired
    private TaskStatusService service;
    
    @RequestMapping(value = "/TaskStatus/", method = RequestMethod.GET)
    public ResponseEntity<List<TaskStatus>> getStatusValues() {
    	System.out.println("Fetching StatusValues");
        List<TaskStatus> resultList = service.getList();
        if(resultList.isEmpty()){
            return new ResponseEntity<List<TaskStatus>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<TaskStatus>>(resultList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/TaskStatus/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<TaskStatus> getStatusValue(@PathVariable("id") int id) {
        System.out.println("Fetching StatusValues with id " + id);
        TaskStatus entity = service.findById(id);
        if (entity == null) {
            logger.info("StatusValue with id " + id + " not found");
            return new ResponseEntity<TaskStatus>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<TaskStatus>(entity, HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/TaskStatus/", method = RequestMethod.POST)
    public ResponseEntity<Void> createStatusValue(@RequestBody TaskStatus entity, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating StatusValue " + entity.getDescription());
        service.create(entity); 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/TaskStatus/{id}").buildAndExpand(entity.getStatusId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/TaskStatus/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TaskStatus> updateStatusValue(@PathVariable("id") int id, @RequestBody TaskStatus entity) {
     	System.out.println("Updating StatusValue " + id);
     
     	TaskStatus currentRecord = service.findById(id);
         
        if (currentRecord==null) {
            System.out.println("StatusValue with id " + id + " not found");
            return new ResponseEntity<TaskStatus>(HttpStatus.NOT_FOUND);
        }
         
        service.update(entity);
        currentRecord = service.findById(id);
        logger.info("current record: " + currentRecord);
        System.out.println("Team  with id " + id + "found");
        return new ResponseEntity<TaskStatus>(currentRecord, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/TaskStatus/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<TaskStatus> deleteStatusValue(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting StatusValue with id " + id);

        TaskStatus record = service.findById(id);
        if (record == null) {
            System.out.println("Unable to delete. StatusValue with id " + id + " not found");
            return new ResponseEntity<TaskStatus>(HttpStatus.NOT_FOUND);
        }
 
        service.delete(id);
        return new ResponseEntity<TaskStatus>(HttpStatus.NO_CONTENT);
    }
}
