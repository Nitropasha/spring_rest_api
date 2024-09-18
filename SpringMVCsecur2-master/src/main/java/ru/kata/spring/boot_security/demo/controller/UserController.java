package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.UserRep;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@Controller
public class UserController {
    //!

    @Autowired
    private UserService userService;
    @Autowired
    UserRep userRepository;

    @RequestMapping(value = "/user")
    public String printUserPage(Principal principal, Model model) {
        User user = userRepository.findByEmail(principal.getName());
        System.err.println(principal.getName());

        model.addAttribute("user", user);
        List<Role> roles = userService.getAllRoles();
        model.addAttribute("allRoles", roles);
        return "user-info2";
    }

    @RequestMapping("user/saveUser2")
    public String saveEmployee2(@Valid @ModelAttribute("user") User user,
                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "user-info";
        } else {
            userService.roleNull(user);
            userService.saveUser(user);
            return "redirect:/user";
        }
    }

}