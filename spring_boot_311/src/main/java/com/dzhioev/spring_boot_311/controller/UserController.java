package com.dzhioev.spring_boot_311.controller;


import com.dzhioev.spring_boot_311.entity.User;
import com.dzhioev.spring_boot_311.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping(value = "/")
    public String printWelcome( Model model) {
        List<User> users = userService.listUsers();
        model.addAttribute("users", users);
        return "users";
    }
    @RequestMapping("/addNewUser")
    public String addNewUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "user-info";
    }
    @RequestMapping("/saveUser")
    public String saveEmployee(@Valid @ModelAttribute("user") User user,
                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "user-info";
        } else {
            userService.saveUser(user);
            return "redirect:/";
        }
    }
    @RequestMapping("/updateInfo")
    public String updateUser(@RequestParam("id") Long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user-info";

    }

    @RequestMapping("/delete")
    public String deleteEmployee (@RequestParam("id") Long id){
        userService.deleteUser(id);
        return "redirect:/";

    }

}