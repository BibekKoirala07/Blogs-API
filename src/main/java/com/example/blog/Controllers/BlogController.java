package com.example.blog.Controllers;

import com.example.blog.models.Blog;
import com.example.blog.models.User;
import com.example.blog.services.BlogService;
import com.example.blog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Blog> getAllBlogs() {
        System.out.println("Get All blogs");
        return blogService.getAllBlogs();
    }

    @GetMapping("/{id}")
    public Optional<Blog> getBlogById(@PathVariable Long id) {
        System.out.println("GetBlogById" + id);
        return blogService.getBlog(id);
    }

    @PostMapping
    public ResponseEntity<Object> createBlog(@Valid @RequestBody Blog blog, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorMessages = new StringBuilder();
            result.getFieldErrors().forEach(error ->
                    errorMessages.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ")
            );
            return new ResponseEntity<>(errorMessages.toString(), HttpStatus.BAD_REQUEST);
        }
        User user = userService.getUserById(blog.getUser().getId()).orElseThrow(() -> new RuntimeException("User not found"));

        blog.setUser(user);

        Blog createdBlog = blogService.createBlog(blog);
        return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public String deleteBlog(@PathVariable  Long id) {
        System.out.println("deleteBlog" + id);
        blogService.deleteBlog(id);
        return new String("The blog with " + id + " has been deleted");
    }

}
