package com.abstractrestapplication.controllers;

import com.abstractrestapplication.models.HateoasResponse;
import com.abstractrestapplication.models.Token;
import com.abstractrestapplication.models.User;
import com.abstractrestapplication.service.AuthenticationService;
import com.abstractrestapplication.utils.HateoasUtil;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    public static final String LOGIN_ERROR_MSG = "Wrong email and/or password";

    AuthenticationService service;

    @Autowired
    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public HateoasResponse login(@RequestBody User loginAttempt, @Context final HttpServletRequest request) throws JOSEException {
        Token token = service.login(loginAttempt, request);
        if (token != null)
            return HateoasUtil.build(token).getBody();
        return HateoasUtil.build(null).getBody();
    }

    @PostMapping("/register")
    public HateoasResponse register(@RequestBody User newUser, HttpServletRequest request) throws JOSEException {
        User user = service.register(newUser, request);
        if (newUser.getId() != null) {
            return HateoasUtil.build(newUser).getBody();
        }
        return login(user, request);
    }
}
