package com.app.controller;

import com.app.model.Login;
import com.app.service.LoginService;
import com.app.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    CurrentUser currentUser;

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        if (currentUser.getId() != null) {
            model.addAttribute("id", currentUser.getId());
            return "allTickets";
        }
        model.addAttribute("login", new Login());
        return "login";
    }

    @PostMapping("/login")
    public String sendCredentials(@ModelAttribute Login login,  Model model) {
        Integer userId = loginService.getUserId(login);
        System.out.println(userId);
        if (userId != null) {
            currentUser.setId(userId);
            currentUser.setName(login.getUsername());
            model.addAttribute("id", userId);
            return "redirect:allTickets";
        }
        model.addAttribute("loginCredentials", login);
        return "login";
    }
}