/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.web.engine.controller;

import com.andrievskaja.business.service.UserService;
import com.andrievskaja.business.service.model.form.RegisteForm;
import com.andrievskaja.business.service.model.form.RecordForm;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Людмила
 */
@Controller
@RequestMapping("/")
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@Valid RegisteForm registeForm,
            BindingResult result,
            ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "Not exit");
            return "/login";
        }
        userService.createUSer(registeForm);
        return "forward:/portal/user/login?" + "email=" + registeForm.getEmail() + "&password=" + registeForm.getPassword();
//        return "public/integration";
    }

}
