package com.securitymodule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AuthenticationService {

    List<String> unauthenticatedUrls;

    @Autowired
    public AuthenticationService(){
        unauthenticatedUrls = new ArrayList<>();
        unauthenticatedUrls.add("/login");
        unauthenticatedUrls.add("/signup");
        unauthenticatedUrls.add("/static/[a-zA-Z0-9_\\-./]*");
    }


    public boolean isAuthorizedUrl(String uri) {
        uri = uri.trim();
        Pattern pattern;
        for(String unauthorizedUrl : unauthenticatedUrls){
            pattern = Pattern.compile(unauthorizedUrl);
            Matcher matcher = pattern.matcher(uri);
            if (matcher.matches()){
                return false;
            }

        }
        return true;
    }
}
