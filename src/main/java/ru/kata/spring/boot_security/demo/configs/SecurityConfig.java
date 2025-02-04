//package ru.kata.spring.boot_security.demo.configs;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())  // Отключаем CSRF
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/", "/index", "/login").permitAll()  // Разрешаем доступ ко всем
//                        .requestMatchers("/admin/**").hasRole("ADMIN")  // Только для администраторов
//                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")  // Для пользователей с ролями USER и ADMIN
//                        .anyRequest().authenticated()  // Для всех остальных запросов требуем авторизацию
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")  // Указываем страницу логина
//                        .loginProcessingUrl("/login")  // URL для обработки формы логина
//                        .defaultSuccessUrl("/user", true)  // Перенаправление по умолчанию на /user после успешного входа
//                        .failureUrl("/login?error=true")  // Перенаправление на страницу с ошибкой
//                        .permitAll()  // Доступ ко всем
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout")  // URL для выхода из системы
//                        .logoutSuccessUrl("/login?logout=true")  // Перенаправление после успешного выхода
//                        .permitAll()  // Доступ ко всем
//                );
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();  // Создаем и используем BCrypt для хеширования паролей
//    }
//}
