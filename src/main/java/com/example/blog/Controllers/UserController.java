package com.example.blog.Controllers;

import com.example.blog.models.User;
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
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        System.out.println("Get All Users");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        System.out.println("Get User By Id {id}" + id );
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user, BindingResult result) {
        System.out.println("createUser " + user + result);
        if (result.hasErrors()) {
            // Prepare a list of error messages with field names
            StringBuilder errorMessages = new StringBuilder();
            result.getFieldErrors().forEach(error ->
                    errorMessages.append(error.getField())
                            .append(": ")
                            .append(error.getDefaultMessage())
                            .append("; ")
            );
            return new ResponseEntity<>(errorMessages.toString(), HttpStatus.BAD_REQUEST);
        }
        User createdUser =  userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        System.out.println("deleteUser" + id);
        userService.deleteUser(id);
    }

}
