package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kata.spring.boot_security.demo.entity.User;

public interface UserRep extends JpaRepository<User,Long> {
    //это больше не надо так как есть анатация Transaction
//    @Query("Select u from User u left join fetch u.roles where u.username=:username")
    User findByUsername(String username);
}
