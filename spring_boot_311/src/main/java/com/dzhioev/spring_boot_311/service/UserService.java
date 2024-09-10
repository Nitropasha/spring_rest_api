package com.dzhioev.spring_boot_311.service;


import com.dzhioev.spring_boot_311.entity.User;

import java.util.List;

public interface UserService {
     public List<User> listUsers();
     public void saveUser(User user);
     public User getUser(Long id);
     public void deleteUser(Long id);
}
