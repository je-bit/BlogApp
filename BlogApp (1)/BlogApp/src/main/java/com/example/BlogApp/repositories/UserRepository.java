package com.example.BlogApp.repositories;

import com.example.BlogApp.models.UserBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserBlog, Long> {

    Optional<UserBlog> findByUsername(String username);

}
