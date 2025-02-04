package ru.kata.spring.boot_security.demo.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.CommandLineRunner;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.HashSet;

@Configuration
public class DataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner dataLoader() {
        return args -> {
            // Удаляем всех пользователей и роли
            userService.deleteAllUsers();
            roleService.deleteAllRoles();

            // Создаем роли
            Role roleAdmin = new Role("ROLE_ADMIN");
            Role roleUser = new Role("ROLE_USER");

            roleService.saveRole(roleAdmin);
            roleService.saveRole(roleUser);

            // Хешируем пароли с использованием BCryptPasswordEncoder
            String rawPasswordAdmin = "admin123";
            String rawPasswordUser = "user123";
            String encodedPasswordAdmin = passwordEncoder.encode(rawPasswordAdmin);
            String encodedPasswordUser = passwordEncoder.encode(rawPasswordUser);

            logger.debug("Encoded Admin Password: {}", encodedPasswordAdmin);
            logger.debug("Encoded User Password: {}", encodedPasswordUser);

            // Создаем пользователей с зашифрованными паролями
            User admin = new User("admin", encodedPasswordAdmin, new HashSet<>());
            admin.getRoles().add(roleAdmin);
            userService.saveUser(admin);

            User user = new User("user", encodedPasswordUser, new HashSet<>());
            user.getRoles().add(roleUser);
            userService.saveUser(user);

            logger.info("Admin and User have been created successfully!");
        };
    }
}
