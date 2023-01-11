package com.example.BlogApp.repositories;

import com.example.BlogApp.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    Optional<Blog> searchByTitleStartingWith(String title);


}