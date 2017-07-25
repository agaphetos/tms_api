package org.agaphetos.api.tms.controller;

import java.util.List;

import org.agaphetos.api.tms.entity.ApplicationType;
import org.agaphetos.api.tms.service.ApplicationTypeService;
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
public class ApplicationTypeController {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationTypeController.class);
	
	@Autowired
    private ApplicationTypeService service;
    
    @RequestMapping(value = "/ApplicationType/", method = RequestMethod.GET)
    public ResponseEntity<List<ApplicationType>> getApplicationTypes() {
    	System.out.println("Fetching ApplicationTypes");
        List<ApplicationType> resultList = service.getList();
        if(resultList.isEmpty()){
            return new ResponseEntity<List<ApplicationType>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<ApplicationType>>(resultList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/ApplicationType/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ApplicationType> getApplicationType(@PathVariable("id") int id) {
        System.out.println("Fetching ApplicationType with id " + id);
        ApplicationType entity = service.findById(id);
        if (entity == null) {
            logger.info("ApplicationType with id " + id + " not found");
            return new ResponseEntity<ApplicationType>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<ApplicationType>(entity, HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/ApplicationType/", method = RequestMethod.POST)
    public ResponseEntity<Void> createApplicationType(@RequestBody ApplicationType entity, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating ApplicationType " + entity.getApplicationName());
        service.create(entity); 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/ApplicationType/{id}").buildAndExpand(entity.getApplicationTypeId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/ApplicationType/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ApplicationType> updateApplicationType(@PathVariable("id") int id, @RequestBody ApplicationType entity) {
     	System.out.println("Updating ApplicationType " + id);
     
     	ApplicationType currentRecord = service.findById(id);
         
        if (currentRecord==null) {
            System.out.println("ApplicationType with id " + id + " not found");
            return new ResponseEntity<ApplicationType>(HttpStatus.NOT_FOUND);
        }
         
        service.update(entity);
        currentRecord = service.findById(id);
        logger.info("current record: " + currentRecord);
        System.out.println("Team  with id " + id + "found");
        return new ResponseEntity<ApplicationType>(currentRecord, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/ApplicationType/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ApplicationType> deleteApplicationType(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting ApplicationType with id " + id);

        ApplicationType record = service.findById(id);
        if (record == null) {
            System.out.println("Unable to delete. ApplicationType with id " + id + " not found");
            return new ResponseEntity<ApplicationType>(HttpStatus.NOT_FOUND);
        }
 
        service.delete(id);
        return new ResponseEntity<ApplicationType>(HttpStatus.NO_CONTENT);
    }
}
