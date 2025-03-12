package com.example.blog.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(min = 3, max = 8, message = "Name must be between 3 and 50 characters")
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @NotNull
    @Min(value = 18, message = "Age must be greater than or equal to 18")
    @Max(value = 100, message = "Age must be less than or equal to 100")
    private Integer age;

    @NotNull
    @Size(min = 6, max = 10, message = "Password must be between 3 and 50 characters")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Blog> blogs;

    public User() {}

    public  User(String name, String email,Integer age, String password) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", blogs=" + blogs +
                '}';
    }

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
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
