package com.demo.rbac.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.demo.rbac.security.JwtUtil;
import com.demo.rbac.service.RbacService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RbacInterceptor implements HandlerInterceptor {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private RbacService rbacService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String path = request.getRequestURI().trim();
	    
	    if (path.startsWith("/api/auth/")) {
	        System.out.println("Skipping RBAC for: " + path);
	        return true;
	    }

	    String authHeader = request.getHeader("Authorization");
	    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.getWriter().write("Invalid or Missing authorization header");
	        return false;
	    }

	    String token = authHeader.substring(7);
	    String username = jwtUtil.extractUserName(token);

	    if (username == null) {
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.getWriter().write("Invalid Or Expired Token");
	        return false;
	    }
	    
	    if (!rbacService.hasAccess(username, path, request.getMethod())) {
	        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	        response.getWriter().write("Access Denied");
	        return false;
	    }

	    return true;
	}

}
