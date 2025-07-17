package com.demo.rbac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SpringBootApplication
public class RoleBasedAccessControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoleBasedAccessControlApplication.class, args);
		
//	    String hash = BCrypt.hashpw("donor@123", BCrypt.gensalt());
//	    System.out.println("New Hash: " + hash);
	}

}
