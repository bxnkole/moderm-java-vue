package com.banks.modernjavavue.filter;

import com.banks.modernjavavue.pojo.User;
import com.banks.modernjavavue.util.TokenAuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException, IOException, ServletException {
        User creds = new ObjectMapper().readValue(req.getInputStream(), User.class);
        String username = creds.getUsername();
        String password = creds.getPassword();
        AuthenticationManager authenticationManager = getAuthenticationManager();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
        Authentication authentication = authenticationManager.authenticate(authToken);
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
        TokenAuthenticationService.addAuthenticationToResponse(res, auth.getName());
    }
}