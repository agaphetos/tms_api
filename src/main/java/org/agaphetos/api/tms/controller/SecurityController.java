package org.agaphetos.api.tms.controller;

import java.util.List;

import org.agaphetos.api.tms.entity.Employee;
import org.agaphetos.api.tms.entity.SecurityAnswer;
import org.agaphetos.api.tms.entity.SecurityQuestion;
import org.agaphetos.api.tms.entity.Token;
import org.agaphetos.api.tms.security.SecurityUtils;
import org.agaphetos.api.tms.service.EmployeeService;
import org.agaphetos.api.tms.service.SecurityAnswerService;
import org.agaphetos.api.tms.service.SecurityQuestionService;
import org.agaphetos.api.tms.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

	private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);
	
    @Autowired
    private EmployeeService userService;

    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private SecurityQuestionService securityQService;
    
    @Autowired
    private SecurityAnswerService securityAService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/Security/Account", method = RequestMethod.GET)
    public @ResponseBody Employee getUserAccount()  {
        Employee employee = userService.findById(Integer.parseInt(SecurityUtils.getCurrentLogin()));
        employee.setPassword(null);
        return employee;
    }

    @RequestMapping(value = "/Security/Tokens", method = RequestMethod.GET)
    public ResponseEntity<List<Token>> getTokens() {
    	logger.info("Fetching Tokens");
        List<Token> tokens = tokenService.getList();
        for(Token t:tokens) {
            t.setSeries(null);
            t.setValue(null);
        }
        return new ResponseEntity<List<Token>>(tokens, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/Security/Questions", method = RequestMethod.GET)
    public ResponseEntity<List<SecurityQuestion>> getSecurityQuestions()  {
    	logger.info("Fetching Security Questions");
        List<SecurityQuestion> resultList = securityQService.getList();
        if(resultList.isEmpty()){
            return new ResponseEntity<List<SecurityQuestion>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<SecurityQuestion>>(resultList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/Security/Answers/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<SecurityAnswer>> getUserSecurityQuestions(@PathVariable("id") int id)  {
    	logger.info("Fetching Security Questions");
    	Employee currentRecord = userService.findById(id);        
        if (currentRecord==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<List<SecurityAnswer>>(HttpStatus.NOT_FOUND);
        } else {
        	if (currentRecord.getFirstLog() != 1) {
	        	List<SecurityAnswer> securityAnswers = securityAService.findByEmployee(currentRecord);
	        	if(securityAnswers==null){
	                return new ResponseEntity<List<SecurityAnswer>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	            } else {
	                return new ResponseEntity<List<SecurityAnswer>>(securityAnswers, HttpStatus.OK);            	
	            }
        	} else {
        		return new ResponseEntity<List<SecurityAnswer>>(HttpStatus.NOT_FOUND);
        	}
        }
    }
    
    @RequestMapping(value = "/Security/Answers/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> updateUserSecurity(@PathVariable("id") int id, @RequestBody List<SecurityAnswer> securityAnswers) {
    	Employee currentRecord = userService.findById(id);        
        if (currentRecord==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
        	for (SecurityAnswer securityAnswer : securityAnswers) {
    			securityAnswer.setAnswer(passwordEncoder.encode(securityAnswer.getAnswer()));
    			securityAnswer.setEmployee(currentRecord);
    			securityAService.create(securityAnswer);
    		}
	        return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/Security/FirstLog/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> updateUserFirstLog(@PathVariable("id") int id) {
    	Employee currentRecord = userService.findById(id);        
        if (currentRecord==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
        	currentRecord.setFirstLog(0);
        	userService.update(currentRecord);
	        return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/Security/Validate/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> validateUserSecurity(@PathVariable("id") int id, @RequestBody List<SecurityAnswer> userAnswers) {
    	Employee currentRecord = userService.findById(id);        
        if (currentRecord==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
        	List<SecurityAnswer> securityAnswers = securityAService.findByEmployee(currentRecord);
        	if (passwordEncoder.matches(userAnswers.get(0).getAnswer(), securityAnswers.get(0).getAnswer())
        			&&
        		passwordEncoder.matches(userAnswers.get(1).getAnswer(), securityAnswers.get(1).getAnswer())) {
    	        return new ResponseEntity<Void>(HttpStatus.OK);
        	}
	        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value = "/Security/Update/{idPassword}", method = RequestMethod.POST)
    public ResponseEntity<Void> updateUserPassword(@PathVariable("idPassword") String s) {
    	String[] param = s.split(",");
    	int id = Integer.parseInt(param[0]);
    	String newPassword = param[1];
    	Employee currentRecord = userService.findById(id);        
        if (currentRecord==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
        	currentRecord.setPassword(passwordEncoder.encode(newPassword.toString()));
        	userService.update(currentRecord);
            System.out.println("User with id " + id + " password update success.");
	        return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/Security/Confirm/{idPassword}", method = RequestMethod.POST)
    public ResponseEntity<Void> validateUserPassword(@PathVariable("idPassword") String s) {
    	String[] param = s.split(",");
    	int id = Integer.parseInt(param[0]);
    	String newPassword = param[1];
    	Employee currentRecord = userService.findById(id);        
        if (currentRecord==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
        	if (passwordEncoder.matches(newPassword, currentRecord.getPassword())){
        		logger.info("Password match confirm.");
    	        return new ResponseEntity<Void>(HttpStatus.OK);
        	}

            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
