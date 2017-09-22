/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.business.service.model.view;

/**
 *
 * @author Людмила
 */
public class UserView {

    private Long id;
    private String email;
    private String password;

    public UserView() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserView(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
