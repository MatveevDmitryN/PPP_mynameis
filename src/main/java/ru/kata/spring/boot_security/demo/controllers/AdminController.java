package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String adminPage(Model model, @AuthenticationPrincipal User authUser) {
        if (authUser == null || authUser.getRoles().stream()
                .noneMatch(role -> role.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/login";
        }
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("authUser", authUser);
        model.addAttribute("userRoles", authUser.getRoles().stream()
                .map(Role::getAuthority)
                .collect(Collectors.toSet()));
        return "admin";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id).orElse(null);
        if (user == null) {
            return "redirect:/error";
        }
        model.addAttribute("user", user);
        return "edit_user";
    }

    @PostMapping("/edit")
    public String updateUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @ExceptionHandler(Exception.class)
    public String handleError(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }
}
