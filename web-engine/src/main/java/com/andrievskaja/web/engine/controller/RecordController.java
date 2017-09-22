/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.web.engine.controller;

/**
 *
 * @author Людмила
 */
import com.andrievskaja.business.model.UserProfile;
import com.andrievskaja.business.service.exception.TaskDeleteException;
import com.andrievskaja.business.service.model.form.RecordForm;
import com.andrievskaja.business.service.model.view.RecordView;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.andrievskaja.business.service.RecordService;

@Controller
@RequestMapping("/portal/user")
@Secured({"ROLE_USER", "ROLE_ADMIN"})
public class RecordController {

    @Autowired
    private RecordService recordService;


    @RequestMapping(method = RequestMethod.GET)
    public String getRecord(Model model, HttpServletRequest request, Principal userProfile) {
        UserProfile user = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<RecordView> list = recordService.getAll(user.getUserId());
        model.addAttribute("records", list);
        return "portal/bloknot";
    }

    @ResponseBody
    @RequestMapping("/add")
    public RecordView add(@Valid RecordForm form, BindingResult result, HttpServletRequest request, Principal userProfile) {
        UserProfile user = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        form.setUserId(user.getUserId());
        return recordService.add(form);
    }

    @ResponseBody
    @RequestMapping("/edit")
    public RecordView edit(@Valid RecordForm form, BindingResult result) throws TaskDeleteException {
        UserProfile user = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        form.setUserId(user.getUserId());
        return recordService.edit(form);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String delete(Long id, Long idTodo) {
        UserProfile user = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        recordService.delete(id, user.getUserId());
        return "ok";
    }

}
