package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import ru.kata.spring.boot_security.demo.service.AdminService;


import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin")
    public String printWelcome(Model model) {
        List<User> users = adminService.allUsers();
        model.addAttribute("users", users);
        // Добавляем пустого пользователя для формы в модальном окне
        model.addAttribute("user", new User());
        List<Role> roles = adminService.getAllRoles();
        model.addAttribute("allRoles", roles);
        return "users";
    }

    @GetMapping("/admin/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        List<Role> roles = adminService.getAllRoles();
        model.addAttribute("allRoles", roles);
        return "user-info";
    }

    @PostMapping("/admin/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users";
        } else {
            adminService.saveUser(user);
            return "redirect:/admin";
        }
    }

    @GetMapping("/admin/updateInfo")
    public String updateUser(@RequestParam("id") Long id, Model model) {
        User user = adminService.getUser(id);
        model.addAttribute("user", user);
        List<Role> roles = adminService.getAllRoles();
        model.addAttribute("allRoles", roles);
        return "user-info";
    }

    @PostMapping("/admin/delete")
    public String deleteEmployee(@RequestParam("id") Long id) {
        adminService.deleteUser(id);
        return "redirect:/admin";
    }

}
