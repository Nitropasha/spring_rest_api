package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.dao.UserDao2;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServes implements  UserDetailsService, UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserDao2 userDao2;

    @Autowired
    RoleDao roleDao;


    public List<User> allUsers() {
        return userDao2.findAll();
    }

    @Autowired
    public void setUserRepository(UserDao userDao){
        this.userDao = userDao;
    }

    public User findByUserName(String username) {
        return userDao2.findByUsername(username);
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    public List<Role> getAllRoles() {
        return (List<Role>) roleDao.findAll();
    }

    @Override
    public User roleNull(User user) {
        return userDao.roleNull(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUserName(username);
        if (user== null) {
            throw new UsernameNotFoundException(String.format("User '%s' not founded", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthority(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthority(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getAuthority())).collect(Collectors.toList());
    }
}
