package com.java.cargo.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.cargo.dto.UserRoleAuthorityDTO;
import com.java.cargo.exceptions.UserExpiredRuntimeException;
import com.java.cargo.model.User;
import com.java.cargo.util.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


public class JWTAuthorizationFilter  extends BasicAuthenticationFilter{

	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws IOException {
		String token = request.getHeader(SecurityConstants.HEADER_STRING);
		if (token != null) {
			// parse the token.
			Claims claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody();
			// check for expiry
			if (new Date().getTime() > claims.getExpiration().getTime()) {
				throw new UserExpiredRuntimeException("User Expired");
			}
			ObjectMapper mapper = new ObjectMapper();
			User user = mapper.readValue(claims.getSubject(), User.class);
			if (user != null) {
				@SuppressWarnings("unchecked")
				Set<UserRoleAuthorityDTO> authorities = (Set<UserRoleAuthorityDTO>) user.getAuthorities();
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>(authorities));
			}
		}
		return null;
	}

}
