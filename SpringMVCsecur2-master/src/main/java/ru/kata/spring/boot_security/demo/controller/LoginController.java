package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    // Метод для отображения страницы логина
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Возвращает шаблон "login.html"
    }


}