package com.example.authdemo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.authdemo.SpringApplicationContext;
import com.example.authdemo.models.requestModels.SignInUserRequestModel;
import com.example.authdemo.services.UserService;
import com.example.authdemo.shared.dto.UserDto;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.AlgorithmConstraints;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager){

        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            SignInUserRequestModel userCreds = new ObjectMapper().readValue(request.getInputStream(), SignInUserRequestModel.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCreds.getEmail(), userCreds.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String email = ((User)authResult.getPrincipal()).getUsername();
        Algorithm signingAlgorithm = Algorithm.HMAC512(SecurityConstants.TOKEN_SECRET);
        String token = JWT.create().withSubject(email).withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME_MS)).withIssuer("testapp").sign(signingAlgorithm);

        UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");

        UserDto userDto = userService.getUser(email);
        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        response.addHeader("UserID", userDto.getUserId());


    }
}
