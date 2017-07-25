package org.agaphetos.api.tms.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.agaphetos.api.tms.entity.Employee;
import org.agaphetos.api.tms.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * Spring Security success handler, specialized for Ajax requests.
 */
@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationSuccessHandler.class);
	
    @Autowired
    private EmployeeService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication)
            throws ServletException, IOException {
    	logger.info("im here for login test - success");
        Employee employee = userService.findById(Integer.parseInt(authentication.getName()));
        SecurityUtils.sendResponse(response, HttpServletResponse.SC_OK, employee);
    }
}
