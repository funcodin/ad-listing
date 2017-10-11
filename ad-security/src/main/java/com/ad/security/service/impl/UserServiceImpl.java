package com.ad.security.service.impl;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.ad.security.service.IUserService;
import com.ad.security.types.User;

@Service
public class UserServiceImpl implements IUserService{

	
	public User getUserByUsername(String username) {
		final User user = new User();
		user.setUsername("rohit");
		user.setPassword("password");
		return user;
	}

	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@PreAuthorize("hasRole('USER')")
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


//	@Override
//	public UserDetails loadUserByUsername( final String username) throws UsernameNotFoundException {
//		User user = null;
//		if (user == null) {
//            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
//        } 
//		return user;
//	}

}
