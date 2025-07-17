package com.demo.rbac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.rbac.security.JwtUtil;
import com.demo.rbac.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/login")
	public String login(@RequestBody LoginRequest request) {

		boolean authenticated = authService.authenticate(request.getUsername(), request.getPassword());

		if (authenticated) {
			return jwtUtil.generateToken(request.getUsername());
		} else {

			throw new RuntimeException("Invalid Username and password");
		}

	}

//Inner claas to map JSON requast to Java object
	static class LoginRequest {
		private String username;
		private String password;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

	}
}
