package com.dzhioev.spring_boot_311.service;

import com.dzhioev.spring_boot_311.dao.UserDao;
import com.dzhioev.spring_boot_311.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;



    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }
    @Transactional
    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }
    @Transactional(readOnly = true)
    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }


}
