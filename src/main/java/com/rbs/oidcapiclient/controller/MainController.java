package com.rbs.oidcapiclient.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {
	private static Logger logger = LoggerFactory.getLogger(MainController.class);
	@GetMapping("/user")
	@PreAuthorize("hasRole(T(com.rbs.botmanager.utile.SecurityConstants).APPROVER) "
			+ "|| hasRole(T(com.rbs.botmanager.utile.SecurityConstants).EDITOR)")
	public String getUsername(Authentication authentication, Principal principal) {
		System.out.println("--------authentication---------");
		System.out.println(authentication.getName());

		System.out.println("--------principal---------");
		System.out.println(principal.getName());

		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("--------user---------" + user.toString());
		return "Success...";
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

		logger.info(currentUserName + " ----------------- ");
		return "";
	}

	// @PreAuthorize("hasRole('AAG-DA-QA-ChatBot-Approver')")
	@PreAuthorize("hasRole(T(com.rbs.botmanager.utile.SecurityConstants).APPROVER) "
			+ "|| hasRole(T(com.rbs.botmanager.utile.SecurityConstants).EDITOR)")
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

}
