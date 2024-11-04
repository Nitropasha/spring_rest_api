package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface AdminService {


    void saveUser(User user);

    User getUser(Long id);

   void deleteUser(Long id);

    List<User> allUsers();





    List<Role> getAllRoles();


}
