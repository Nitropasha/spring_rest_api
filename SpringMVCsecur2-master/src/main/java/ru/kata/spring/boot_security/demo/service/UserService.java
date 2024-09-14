package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService {
    User findByUserName (String string);
    void saveUser(User user);
    User roleNull (User user);
    List<Role> getAllRoles();
}
