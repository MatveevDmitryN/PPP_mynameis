package ru.kata.spring.boot_security.demo.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Название роли, например "ROLE_USER", "ROLE_ADMIN"
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    // Пустой конструктор для JPA
    public Role() {}

    // Конструктор с именем роли
    public Role(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Role name cannot be null or empty");
        }
        this.name = name;
    }

    // Метод для получения роли (используется в Spring Security)
    @Override
    public String getAuthority() {
        return name;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Role name cannot be null or empty");
        }
        // Обеспечиваем, чтобы роль начиналась с "ROLE_"
        if (!name.startsWith("ROLE_")) {
            throw new IllegalArgumentException("Role name must start with 'ROLE_'");
        }
        this.name = name;
    }

    // Переопределение equals() и hashCode() для корректной работы в коллекциях и при использовании JPA
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    // Перегрузка toString() для удобства вывода
    @Override
    public String toString() {
        return "Role{id=" + id + ", name='" + name + "'}";
    }
}
