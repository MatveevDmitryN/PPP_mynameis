package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
public class UserController {

    private final UserService userService;  // Добавляем UserService

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String userPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        // Получаем пользователя из базы по имени
        User authUser = userService.getUserByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found in database"));

        model.addAttribute("authUser", authUser);
        return "user";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }
}

