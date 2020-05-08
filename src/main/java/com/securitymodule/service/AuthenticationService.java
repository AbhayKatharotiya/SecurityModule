package com.securitymodule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthenticationService {

    List<String> unauthenticatedUrls;

    @Autowired
    public AuthenticationService(){
        unauthenticatedUrls = new ArrayList<>();
        unauthenticatedUrls.add("/login");
        unauthenticatedUrls.add("/signup");
        unauthenticatedUrls.add("/logout");
    }


    public boolean isAuthorizedUrl(String uri) {
        uri = uri.trim();
        return !unauthenticatedUrls.contains(uri);
    }
}
