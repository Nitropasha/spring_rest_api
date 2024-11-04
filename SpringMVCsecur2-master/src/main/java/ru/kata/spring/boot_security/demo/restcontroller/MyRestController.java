package ru.kata.spring.boot_security.demo.restcontroller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.AdminService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class MyRestController {

    private final AdminService adminService;

    private final UserService userService;

    public MyRestController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(Authentication authentication) {
        if (authentication != null) {
            User user = userService.findByEmail(authentication.getName());
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {
        List<User> users = adminService.allUsers();
        return users;
    }


    @GetMapping("/users/{id}")
    public User getUser(@PathVariable long id) {
        User user = adminService.getUser(id);
        return user;
    }
    @CrossOrigin
    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        adminService.saveUser(user);
        return  user;
    }

    @PostMapping("/users")
    public User addNewEmployee (@RequestBody User user) {
        adminService.saveUser(user);
        return user;
    }
    @CrossOrigin
    @DeleteMapping("/users/{id}")
    public String deleteUser (@PathVariable long id) {
        User user = adminService.getUser(id);
        adminService.deleteUser(id);
        return "OK ALL RIGHT";
    }

}
