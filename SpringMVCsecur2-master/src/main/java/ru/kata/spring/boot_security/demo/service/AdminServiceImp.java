package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.RoleRep;
import ru.kata.spring.boot_security.demo.repository.UserRep;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userRep.findAll();
    }

    @Transactional
    @Override
    public void saveUser(User user) {

        System.err.println("вошли сюда " + user.getPassword().length());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        System.err.println("вошли сюда " + user.getPassword().length());
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
    public User roleNull(User user) {
        Role userRole = roleRep.findById(1L)
                .orElseThrow(() -> new RuntimeException("Role USER not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);
        return user;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRep.findAll();
    }

    public User findByUserName(String username) {
        return userRep.findByUsername(username);
    }
}
