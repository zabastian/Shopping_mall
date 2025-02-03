package com.example.shopping_mall.auth.repository;

import com.example.shopping_mall.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository <User, Long> {

    Optional<User> findUserByEmail(String email);

}
