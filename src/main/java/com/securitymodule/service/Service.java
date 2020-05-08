package com.securitymodule.service;

import com.securitymodule.exception.LoginFailedException;
import com.securitymodule.exception.RegistrationFailedException;
import com.securitymodule.model.Constants;
import com.securitymodule.model.LoginModel;
import com.securitymodule.model.User;
import com.securitymodule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private UserRepository userRepository;

    public String regster(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        try {
            userRepository.save(user);
            return Constants.REGISTERED;
        } catch (Exception e) {
            throw new RegistrationFailedException("User registration failed");
        }
    }

    public String authenticate(LoginModel loginModel) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = userRepository.findByEmail(loginModel.getUsername());
        if (user != null) {
            if (encoder.matches(loginModel.getPassword(), user.getPassword()))
                return Constants.LOGIN_SUCCESSFUL;
            else
                throw new LoginFailedException(Constants.INVALID_LOGIN);
        } else
            throw new LoginFailedException(Constants.INVALID_LOGIN);
    }
}
