package com.securitymodule.controller;

import com.securitymodule.model.Constants;
import com.securitymodule.model.LoginModel;
import com.securitymodule.model.User;
import com.securitymodule.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class Controller {

    @Autowired
    private Service service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginModel loginModel, HttpServletRequest request, HttpServletResponse response) {
        String result = service.authenticate(loginModel);
        if (result.equals(Constants.LOGIN_SUCCESSFUL)) {
            request.getSession().setAttribute("LOGIN_SESSION", loginModel.getUsername());
            request.getSession().setMaxInactiveInterval(60);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody User user) {
        return ResponseEntity.ok(service.regster(user));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return ResponseEntity.ok(Constants.SUCCESS);
    }

    @GetMapping("/")
    public String testApi(){
        return "Hello there";
    }

}
