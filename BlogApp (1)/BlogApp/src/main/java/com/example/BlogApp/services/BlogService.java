package com.example.BlogApp.services;

import com.example.BlogApp.models.Blog;
import com.example.BlogApp.repositories.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Blog findById(long id) {
        Blog blog = blogRepository.findById(id).orElse(null);

        return blog;
    }

    public List<Blog> searchTitle(String title){
        List<Blog> blogs = this.blogRepository.searchByTitleStartingWith(title).stream().toList();
        return blogs;
    }
    public List<Blog> findAll() {
        return this.blogRepository.findAll().stream().collect(Collectors.toList());
    }

    public void addBlog(Blog blog) {
//        blog.setDateAdded(new Date());
        this.blogRepository.save(blog);

    }

    public void deleteBlog(long id) {
        this.blogRepository.deleteById(id);

    }

    public void edit(Blog blog) {
        this.blogRepository.save(blog);
    }
}
