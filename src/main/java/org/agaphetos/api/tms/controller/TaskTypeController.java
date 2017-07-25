package org.agaphetos.api.tms.controller;

import java.util.List;

import org.agaphetos.api.tms.entity.TaskType;
import org.agaphetos.api.tms.service.TaskTypeService;
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
public class TaskTypeController {

	private static final Logger logger = LoggerFactory.getLogger(TaskTypeController.class);
	
	@Autowired
    private TaskTypeService service;
    
    @RequestMapping(value = "/TaskType/", method = RequestMethod.GET)
    public ResponseEntity<List<TaskType>> getTaskTypes() {
    	System.out.println("Fetching TaskTypes");
        List<TaskType> resultList = service.getList();
        if(resultList.isEmpty()){
            return new ResponseEntity<List<TaskType>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<TaskType>>(resultList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/TaskType/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<TaskType> getTaskType(@PathVariable("id") int id) {
        System.out.println("Fetching TaskType with id " + id);
        TaskType entity = service.findById(id);
        if (entity == null) {
            logger.info("TaskType with id " + id + " not found");
            return new ResponseEntity<TaskType>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<TaskType>(entity, HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/TaskType/", method = RequestMethod.POST)
    public ResponseEntity<Void> createTaskType(@RequestBody TaskType entity, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating TaskType " + entity.getDescription());
        service.create(entity); 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/TaskType/{id}").buildAndExpand(entity.getTaskTypeId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/TaskType/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TaskType> updateTaskType(@PathVariable("id") int id, @RequestBody TaskType entity) {
     	System.out.println("Updating TaskType " + id);
     
     	TaskType currentRecord = service.findById(id);
         
        if (currentRecord==null) {
            System.out.println("StatusValue with id " + id + " not found");
            return new ResponseEntity<TaskType>(HttpStatus.NOT_FOUND);
        }
         
        service.update(entity);
        currentRecord = service.findById(id);
        logger.info("current record: " + currentRecord);
        System.out.println("Team  with id " + id + "found");
        return new ResponseEntity<TaskType>(currentRecord, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/TaskType/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<TaskType> deleteTaskType(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting TaskType with id " + id);

        TaskType record = service.findById(id);
        if (record == null) {
            System.out.println("Unable to delete. TaskType with id " + id + " not found");
            return new ResponseEntity<TaskType>(HttpStatus.NOT_FOUND);
        }
 
        service.delete(id);
        return new ResponseEntity<TaskType>(HttpStatus.NO_CONTENT);
    }
}
