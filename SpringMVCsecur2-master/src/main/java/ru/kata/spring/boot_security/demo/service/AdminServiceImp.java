package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.RoleRep;
import ru.kata.spring.boot_security.demo.repository.UserRep;


import java.util.List;


@Service

public class AdminServiceImp implements AdminService {

    private final UserRep userRep;
    private final RoleRep roleRep;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminServiceImp(UserRep userRep, RoleRep roleRep, PasswordEncoder passwordEncoder) {
        this.userRep = userRep;
        this.roleRep = roleRep;
        this.passwordEncoder = passwordEncoder;

    }

    @Transactional
    @Override
    public void saveUser(User user) {
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
        } else {
            userRep.findById(user.getId()).ifPresent(existingUser -> user.setPassword(existingUser.getPassword()));
        }
        userRep.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUser(Long id) {
        return userRep.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        System.err.println("ЮЗЕР НАЙДЕН" + id);
        userRep.deleteById(id);
    }

    @Override
    public List<User> allUsers() {
        return userRep.findAll();
    }



    @Override
    public List<Role> getAllRoles() {
        return roleRep.findAll();
    }

}
