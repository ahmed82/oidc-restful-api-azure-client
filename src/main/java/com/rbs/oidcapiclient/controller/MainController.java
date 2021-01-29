package com.rbs.oidcapiclient.controller;

import java.lang.reflect.Field;
import java.security.Principal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rbs.oidcapiclient.constant.AuthoritiesConstants;


@RestController
public class MainController {
	
	private static Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@GetMapping("/user")
	@PreAuthorize("hasRole(T(com.rbs.botmanager.utile.SecurityConstants).APPROVER) "
			+ "|| hasRole(T(com.rbs.botmanager.utile.SecurityConstants).EDITOR)")
	public String getUsername() {
		
		if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
			String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
			return currentUserName;
		}
		
		Authentication authentication2 = SecurityContextHolder.getContext().getAuthentication();
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication2.getAuthorities();

		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.info("--------user---------" + authorities.toString());
		return "Success..."+authorities.toString();
	}

	@PreAuthorize("hasRole('AAG-DA-QA-ChatBot-Approver')")
	@GetMapping("/test7")
	public String getName2(Authentication authentication, Principal principal) {
		logger.info(authentication.getName());
		logger.info("-----------------");
		logger.info(principal.getName());
		// System.out.println(principal.);
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.info("-----------------" + user.toString());
		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		// UserDetails userDetails = (UserDetails)
		// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Field [] attributes =  principal.getClass().getDeclaredFields();
		for (Field field : attributes) {
            // Dynamically read Attribute Name
            System.out.println("ATTRIBUTE NAME: " + field.getName());
            if(field.getName().equals("attributes")) {
            	Field [] jwtclimsset =  field.getClass().getDeclaredFields();
            	for (Field climsset : jwtclimsset) {
                    // Dynamically read Attribute Name
                    System.out.println("jwtClaimsSet: " + climsset.getName());
            	}
            }
		}
		logger.info(currentUserName + " ----------------- ");
		return "";
	}

	// @PreAuthorize("hasRole('AAG-DA-QA-ChatBot-Approver')")
	@PreAuthorize("hasRole('" + AuthoritiesConstants.USER + "')" +
            " && hasRole('" + AuthoritiesConstants.ADMIN + "')" )
	@RequestMapping(value = "/test3", method = RequestMethod.GET)
	@ResponseBody
	public String currentUserName(Authentication authentication, Principal principal) {
		
		logger.info("########111##############" + authentication.getName() + "#########222#############"
				+ authentication.getDetails() + "#########33#############" + authentication.getPrincipal()
				+ "##########444############" + authentication.getCredentials() + "#########555#############"
				+ authentication.getAuthorities() + "##########666############"
				+ authentication.getPrincipal().toString() + "##########777############" + principal.getName());
		return authentication.getName() + authentication.getAuthorities();
	}
	
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserNameSimple(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return principal.getName();
    }

}
