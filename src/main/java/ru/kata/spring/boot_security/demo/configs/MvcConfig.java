package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // Регистрация контроллеров представлений
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Статическая страница для главной страницы
        registry.addViewController("/").setViewName("index");

        // Страница входа
        registry.addViewController("/login").setViewName("login");

        // Добавление страницы для ошибки доступа (например, 403)
        registry.addViewController("/access-denied").setViewName("access-denied");

        // Добавление страницы для ошибки (например, 404)
        registry.addViewController("/error").setViewName("error");

        // Это страницы, которые не требуют логики контроллеров
        // Но для этих маршрутов у вас должны быть соответствующие контроллеры
    }
}
