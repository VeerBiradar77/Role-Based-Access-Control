package com.demo.rbac.security;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;

	public JwtAuthenticationFilter(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");

		// Check if header starts with Baerer prefix
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			// remove bearer to get the token only
			String token = authHeader.substring(7);
			//extract uname from the token
			String username = jwtUtil.extractUserName(token);

			// If uname found and no auth does for this request
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				// create an auth token
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						username, null, new ArrayList<>());

				// keep it in spring security context so next filters see it as authenticated
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}

		// it will pass request to the next filter in chain
		filterChain.doFilter(request, response);
	}
}
