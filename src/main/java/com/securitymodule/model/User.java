package com.securitymodule.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "user_master")
public class User {

    private Long id;
    private String name;
    private String contactNo;

    private String email;
    private String password;
    private String address;

    public User() {
        super();
    }

    public User(String name, String contactNo, String email, String password, String address) {
        this.name = name;
        this.contactNo = contactNo;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    @Id
    @Column(name = "user_master_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    @NotBlank(message = "Please enter name.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "contact_no")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Please enter valid contact no")
    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    @Column(name = "email", unique = true)
    @Pattern(regexp = "(^$|[a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})", message = "Please enter valid email")

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    @NotBlank(message = "Please enter password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
