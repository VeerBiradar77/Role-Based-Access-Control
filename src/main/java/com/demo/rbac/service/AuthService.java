package com.demo.rbac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.demo.rbac.model.User;
import com.demo.rbac.repository.UserRepository;

@Service
public class AuthService {
	@Autowired
	private UserRepository userRepository;
	
	public boolean authenticate(String username, String password) {
	    User user = userRepository.findByUserName(username);

	    if (user == null) {
	        System.out.println("User not found: " + username);
	        return false;
	    }

	    System.out.println("User found: " + username);
	    System.out.println("DB Password: " + user.getPassword());
	    System.out.println("Raw Password: " + password);
	    // Check if raw password matches DB hash
	    boolean match = BCrypt.checkpw(password, user.getPassword());
	    System.out.println("BCrypt Match: " + match);

	    // Return true only if user is active and password matches
	    return user.isActive() && match;
	}

}
