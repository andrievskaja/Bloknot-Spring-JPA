/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.business.service.impl;

import com.andrievskaja.business.model.User;
import com.andrievskaja.business.service.UserService;
import com.andrievskaja.business.service.model.form.RegisteForm;
import com.andrievskaja.business.service.model.view.UserView;
import com.andrievskaja.dao.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Людмила
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    @Transactional
    public UserView createUSer(RegisteForm registeForm) {
        User user = new User();
        user.setEmail(registeForm.getEmail());
        user.setLogin(registeForm.getEmail());
//        user.setPassword(registeForm.getPassword());
        user.setPassword(passwordEncoder.encode(registeForm.getPassword()));
        user.getRoles().add("ROLE_USER");
        userRepository.save(user);
        return mapper.map(user, UserView.class);
    }

}
