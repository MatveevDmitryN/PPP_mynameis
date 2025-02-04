package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(Long id);
    void saveRole(Role role);
    void deleteAllRoles(); // Добавьте этот метод
}
