package com.rbs.oidcapiclient.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@GetMapping("/user")
	public String getName(Authentication authentication, Principal principal) {
		System.out.println("--------authentication---------");
		System.out.println(authentication.getName());

		System.out.println("--------principal---------");
		System.out.println(principal.getName());

		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("--------user---------" + user.toString());
		return "";
	}

}
