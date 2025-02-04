package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // Регистрация контроллеров представлений
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Добавляем контроллер для маршрута /user, который будет отображать представление "user"
        registry.addViewController("/user").setViewName("user");

        // Можно также добавить другие маршруты, например:
        // Регистрация страницы для главной
        registry.addViewController("/").setViewName("index");

        // Страница входа
        registry.addViewController("/login").setViewName("login");

        // Добавить страницы для админа, если нужно
        registry.addViewController("/admin").setViewName("admin");
    }
}
