package org.agaphetos.api.tms.controller;

import java.util.List;

import org.agaphetos.api.tms.entity.UserRole;
import org.agaphetos.api.tms.service.UserRoleService;
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
public class UserRoleController {

	private static final Logger logger = LoggerFactory.getLogger(UserRoleController.class);

    @Autowired
    private UserRoleService service;
    
    @RequestMapping(value = "/UserRole/", method = RequestMethod.GET)
    public ResponseEntity<List<UserRole>> getUserRoles() {
    	System.out.println("Fetching User Roles");
        List<UserRole> resultList = service.getList();
        if(resultList.isEmpty()){
            return new ResponseEntity<List<UserRole>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<UserRole>>(resultList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/UserRole/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserRole> getUserRole(@PathVariable("id") int id) {
        System.out.println("Fetching User with id " + id);
        UserRole u = service.findById(id);
        if (u == null) {
            logger.info("UserRole with id " + id + " not found");
            return new ResponseEntity<UserRole>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserRole>(u, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/UserRole/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUserRole(@RequestBody UserRole entity, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating UserRole " + entity.getDescription());
 
        if (service.findByDescription(entity.getDescription()) != null) {
            System.out.println("A UserRole with name " + entity.getDescription() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        service.create(entity);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/UserRole/{id}").buildAndExpand(entity.getRoleId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/UserRole/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserRole> updateUserRole(@PathVariable("id") int id, @RequestBody UserRole entity) {
     	System.out.println("Updating User " + id);
     
     	UserRole currentRecord = service.findById(id);
         
        if (currentRecord==null) {
            System.out.println("UserRole with id " + id + " not found");
            return new ResponseEntity<UserRole>(HttpStatus.NOT_FOUND);
        }
         
        service.update(entity);
        currentRecord = service.findById(id);
        logger.info("current userRole: " + currentRecord);
        System.out.println("UserRole with id " + id + "found");
        return new ResponseEntity<UserRole>(currentRecord, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/UserRole/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<UserRole> deleteUserRole(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting User with id " + id);

        UserRole record = service.findById(id);
        if (record == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<UserRole>(HttpStatus.NOT_FOUND);
        }
 
        service.delete(id);
        return new ResponseEntity<UserRole>(HttpStatus.NO_CONTENT);
    }
}
