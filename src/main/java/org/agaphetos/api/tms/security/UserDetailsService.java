package org.agaphetos.api.tms.security;

import java.util.ArrayList;
import java.util.Collection;

import org.agaphetos.api.tms.entity.Employee;
import org.agaphetos.api.tms.entity.UserRole;
import org.agaphetos.api.tms.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private EmployeeService userService;

    @Override
    public UserDetails loadUserByUsername(final String s) {
    	logger.info("Authenticating {}", s);
    	Employee employee;
    	try {
    		employee = userService.findById(Integer.parseInt(s));
    		if (employee == null) {
        		throw new UsernameNotFoundException("User " + s + " was not found in the database");
            } else if (employee.getStatus() != 1) {
                throw new UserNotEnabledException("User " + s + " was not enabled");
            }
    	} catch(NumberFormatException nfe) {
    		employee = userService.findByEmail(s);
        	if (employee == null) {
        		throw new UsernameNotFoundException("User " + s + " was not found in the database");
        	}
    	}

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        UserRole userRole = employee.getUserRole();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userRole.getDescription());
        grantedAuthorities.add(grantedAuthority);
        return new org.springframework.security.core.userdetails.User(Integer.toString(employee.getEmployeeId()), employee.getPassword(),
                grantedAuthorities);
    }
}
