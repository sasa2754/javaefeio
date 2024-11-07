package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmail(String email);
    List<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE (:email IS NULL OR u.email = :email) OR (:username IS NULL OR u.username = :username)")
    // List<User> findByEmailOrUsername(String email, String username);
    Optional<User> findByEmailOrUsername(String email, String username); // Se não existir, retorna null, se existir, retorna o valor, da rpra usar métodos os isPresent()
}
