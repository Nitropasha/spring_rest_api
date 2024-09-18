package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import ru.kata.spring.boot_security.demo.service.UserService;


import java.security.Principal;
import java.util.*;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user")
    public String printUserPage(Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName());
        System.err.println(principal.getName());
        model.addAttribute("user", user);
        List<Role> roles = userService.getAllRoles();
        model.addAttribute("allRoles", roles);
        return "user-info2";
    }



}