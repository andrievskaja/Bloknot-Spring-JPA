/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.business.service.impl;

import com.andrievskaja.business.model.User;
import com.andrievskaja.business.model.UserProfile;
import com.andrievskaja.dao.UserRepository;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Людмила
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        Long userId = user.getId();
        String password = user.getPassword();
        Set<String> roles = user.getRoles();
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.stream().forEach((role) -> {
            authorities.add(new SimpleGrantedAuthority(role));
        });
        if (user instanceof User) {
            return new UserProfile(userId, username, password,authorities);
        }
        throw new UsernameNotFoundException("Unknown user role");

    }
}
