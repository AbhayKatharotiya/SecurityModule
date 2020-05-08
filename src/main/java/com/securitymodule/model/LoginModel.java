package com.securitymodule.model;

import javax.validation.constraints.NotBlank;

public class LoginModel {

    private String username;
    private String password;

    public LoginModel() {
        super();
    }

    public LoginModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @NotBlank(message = "Please enter username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank(message = "Please enter password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
