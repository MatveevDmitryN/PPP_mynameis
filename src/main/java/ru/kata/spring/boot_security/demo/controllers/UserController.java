package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.User;

@Controller
public class UserController {

    @GetMapping("/user")
    public String userPage(Model model, @AuthenticationPrincipal User authUser) {
        if (authUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("authUser", authUser);
        return "user";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }
}
