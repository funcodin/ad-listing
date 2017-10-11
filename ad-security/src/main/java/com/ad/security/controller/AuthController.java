package com.ad.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ad.security.service.IUserService;
import com.ad.security.service.auth.token.TokenHelper;

@RestController
public class AuthController {

	@Autowired
	IUserService ser;
	
	@Autowired
	TokenHelper tokenHelper;
	
	@RequestMapping( value="/test", method=RequestMethod.GET)
	public void getCont(){
		ser.findByUsername("");
		System.out.println("Reached");
	}
	
	@RequestMapping( value="/login", method=RequestMethod.GET)
	public String getContt(){
		//ser.findByUsername("");
		System.out.println("Reached");
		return tokenHelper.generateToken("test");
	}
}
