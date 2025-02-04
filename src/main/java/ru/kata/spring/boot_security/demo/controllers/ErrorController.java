package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/access-denied")
    public String accessDenied() {
        return "accessDenied";  // Страница, которая будет отображена при ошибке доступа
    }

    @RequestMapping("/custom-error")
    public String handleError() {
        // Здесь можно вернуть страницу для отображения ошибок (например, 500)
        return "error";  // Убедитесь, что есть такой файл error.html
    }
}
