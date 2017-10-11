package com.ad.security.service;

import java.util.List;

import com.ad.security.types.User;

public interface IUserService {
	//Patience perseverance 
	public User findById(Long id);
    public User findByUsername(String username);
    public List<User> findAll ();
	
}
