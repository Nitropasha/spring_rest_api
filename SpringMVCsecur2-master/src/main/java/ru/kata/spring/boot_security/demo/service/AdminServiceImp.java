package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.RoleRep;
import ru.kata.spring.boot_security.demo.repository.UserRep;

import java.util.List;

@Service
@Transactional
public class AdminServiceImp implements AdminService {

    private final UserRep userRep;
    private final RoleRep roleRep;

    public AdminServiceImp(UserRep userRep, RoleRep roleRep) {
        this.userRep = userRep;
        this.roleRep = roleRep;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userRep.findAll();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
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

    public User findByUserName(String username) {
        return userRep.findByUsername(username);
    }
}
