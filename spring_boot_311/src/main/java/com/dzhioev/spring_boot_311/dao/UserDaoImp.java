package com.dzhioev.spring_boot_311.dao;


import com.dzhioev.spring_boot_311.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDaoImp implements UserDao {


   @PersistenceContext
   private EntityManager entityManager;

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = entityManager.createQuery("from User", User.class);
      return query.getResultList();
   }

   @Override
   public void saveUser(User user) {
      if (user.getId() == null) {
         entityManager.persist(user);
      } else {
         entityManager.merge(user);
      }
   }

   @Override
   public User getUser(Long id) {
      User user = entityManager.find(User.class, id);
      return user;
   }

   @Override
   public void deleteUser(Long id) {
      User user = entityManager.find(User.class, id);
      if (user != null) {
         entityManager.remove(user);
      }

   }


}
