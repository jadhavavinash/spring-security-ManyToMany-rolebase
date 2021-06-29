package com.java.cargo.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.cargo.rest.repository.IUserRepository;
import com.java.cargo.security.Token;
import com.java.cargo.service.IUserService;
import com.java.cargo.util.SecurityConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	

	@Autowired
	IUserService userService;

	@Autowired
	IUserRepository userRepository;

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(String url, AuthenticationManager authenticationManager, IUserService userService,
			IUserRepository userRepository) {
		super(new AntPathRequestMatcher(url));
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.userRepository = userRepository;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			com.java.cargo.model.User creds = new ObjectMapper().readValue(req.getInputStream(),
					com.java.cargo.model.User.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUserName(),
					creds.getPassword(), Collections.emptyList()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		 Token authenticatedUser=userService.getUserByName(((User) auth.getPrincipal()).getUsername());
		
		 ObjectMapper mapper = new ObjectMapper();
		 String userJsonString = mapper.writeValueAsString(authenticatedUser);
		 //String  TOKEN_EXPIRATION_TIME=userService.getSystemConstantByKey("TOKEN_EXPIRATION_TIME");
		 String jwtToken = Jwts.builder().setSubject(userJsonString)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION_TIME))
				//.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET).compact();
		res.addHeader(SecurityConstants.HEADER_STRING, jwtToken);
		res.setStatus(HttpServletResponse.SC_OK);
		res.getWriter().write("Success");

	}

	@Override
	protected synchronized void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String str = exception.getMessage(); // this gives different msg for each exception
		if (str.equalsIgnoreCase("User account is locked")) {
			response.setStatus(HttpServletResponse.SC_GATEWAY_TIMEOUT);// EXPECTATION_FAILED);
			response.getWriter().write("User account is locked");
		} else if (str.equalsIgnoreCase("User is disabled")) {
			response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);// TEMPORARY_REDIRECT);
			response.getWriter().write("User is disabled");
		} else if (str.equalsIgnoreCase("User account has expired")) {
			response.setStatus(HttpServletResponse.SC_HTTP_VERSION_NOT_SUPPORTED);// NOT_ACCEPTABLE);
			response.getWriter().write("User account has expired");
		} else if (str.equalsIgnoreCase("User credentials have expired")) {
			response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);// MODIFIED);
			response.getWriter().write("User credentials have expired");
		} else if (str.equalsIgnoreCase("Bad credentials")) {
			response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
			response.getWriter().write("Bad credentials");
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);// NOT_FOUND);
			response.getWriter().write("user Not found");
		}

	}


}
