package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.repository.RoleRep;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import ru.kata.spring.boot_security.demo.service.AdminService;


import javax.validation.Valid;
import java.util.List;
@Controller
public class AdminController {


    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/admin")
    public String printWelcome(Model model) {
        List<User> users = adminService.allUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping("/admin/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        List<Role> roles = adminService.getAllRoles();
        model.addAttribute("allRoles", roles);
        return "user-info";
    }

    @RequestMapping("admin/saveUser")
    public String saveEmployee(@Valid @ModelAttribute("user") User user,
                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "user-info";
        } else {
            adminService.saveUser(user);
            return "redirect:/admin";
        }
    }


    @RequestMapping("admin/updateInfo")
    public String updateUser(@RequestParam("id") Long id, Model model) {
        User user = adminService.getUser(id);
        model.addAttribute("user", user);
        List<Role> roles = adminService.getAllRoles();
        model.addAttribute("allRoles", roles);
        return "user-info";
    }

    @RequestMapping("admin/delete")
    public String deleteEmployee(@RequestParam("id") Long id) {
        adminService.deleteUser(id);
        return "redirect:/admin";

    }

}