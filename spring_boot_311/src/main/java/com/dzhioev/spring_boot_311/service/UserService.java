package com.dzhioev.spring_boot_311.service;


import com.dzhioev.spring_boot_311.entity.User;

import java.util.List;

public interface UserService {
      List<User> listUsers();
      void saveUser(User user);
      User getUser(Long id);
      void deleteUser(Long id);
}
