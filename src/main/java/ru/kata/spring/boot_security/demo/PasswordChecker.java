package ru.kata.spring.boot_security.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordChecker {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String rawPassword = "admin123";
        String encodedPasswordFromDB = "$2a$10$Og.ph0pdLPfmzn2hmILNxO4IzZ3k6wnEtvdW6qd6dkL21f7U.el9u"; // Пароль из логов

        boolean matches = passwordEncoder.matches(rawPassword, encodedPasswordFromDB);
        System.out.println("Password matches: " + matches);
    }
}





