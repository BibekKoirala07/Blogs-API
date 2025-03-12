package com.example.blog.services;

import com.example.blog.models.Blog;
import com.example.blog.repository.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepo blogRepo;

    public List<Blog>  getAllBlogs() {
        return blogRepo.findAll();
    }

    public Optional<Blog> getBlog(Long id) {
        return blogRepo.findById(id);
    }

    public Blog createBlog(Blog blog) {
        return blogRepo.save(blog);
    }

    public void deleteBlog(Long id) {
        blogRepo.deleteById(id);
    }
}
