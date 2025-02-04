package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    // Добавляем метод для поиска роли по имени
    Optional<Role> findByName(String name);
}
