/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.business.model;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Людмила
 */
public class UserProfile extends User {

    private final Long userId;


    public UserProfile(Long userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userId = userId;

    }

    public UserProfile(Long userId, String username, String password, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, true, true, true, accountNonLocked, authorities);
        this.userId = userId;

    }

    public Long getUserId() {
        return userId;
    }


//    public boolean isAdmin() {
//        return getAuthorities() != null && getAuthorities().contains(new SimpleGrantedAuthority(Role.ROLE_ADMIN));
//    }

}
