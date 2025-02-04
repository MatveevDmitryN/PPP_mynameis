package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Метод для подгрузки пользователя с ролями по имени
    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.username = :username")
    Optional<User> findByUsername(String username);

    // Метод для проверки существования пользователя с таким именем
    boolean existsByUsername(String username);  // Проверяет, существует ли пользователь с таким именем

}
