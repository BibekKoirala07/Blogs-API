
# Spring Boot Blog Application

A RESTful blog application built with Spring Boot that allows users to create, read, update, and delete blog posts.

## Features

- User management with validation
- Blog post creation and management
- RESTful API endpoints
- Proper error handling
- JPA/Hibernate integration for database operations

## Project Structure

```
com.example.blog
├── controllers
│   ├── BlogController.java
│   └── UserController.java
├── exceptions
│   └── GlobalException.java
├── models
│   ├── Blog.java
│   └── User.java
├── repository
│   ├── BlogRepo.java
│   └── UserRepo.java
├── services
│   ├── BlogService.java
│   └── UserService.java
```

## Models

### User

```java
@Entity
@Table(name = "users")
```

- **id**: Auto-generated primary key
- **name**: String (3-8 characters)
- **email**: Valid email format
- **age**: Integer (18-100)
- **password**: String (6-10 characters)
- **blogs**: One-to-many relationship with Blog entities

### Blog

```java
@Entity
```

- **id**: Auto-generated primary key
- **title**: String (5-100 characters)
- **description**: String (5-100 characters)
- **user**: Many-to-one relationship with User entity

## API Endpoints

### User Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | `/api/users` | Get all users |
| GET | `/api/users/{id}` | Get user by ID |
| POST | `/api/users` | Create a new user |
| DELETE | `/api/users/{id}` | Delete a user |

### Blog Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | `/api/blogs` | Get all blogs |
| GET | `/api/blogs/{id}` | Get blog by ID |
| POST | `/api/blogs` | Create a new blog |
| DELETE | `/api/blogs/{id}` | Delete a blog |

## Validation

The application implements validation for both User and Blog entities:

- **User**:
    - Name: 3-8 characters, not blank
    - Email: Valid email format
    - Age: Between 18 and 100
    - Password: 6-10 characters

- **Blog**:
    - Title: 5-100 characters, not blank
    - Description: 5-100 characters, not null

## Error Handling

The application has global exception handling using `@ControllerAdvice`:

- Handles 404 Not Found errors
- Provides appropriate error messages for validation errors
- General exception handling for unexpected errors

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Your preferred IDE (IntelliJ IDEA, Eclipse, etc.)

### Database Configuration

Add the following properties to your `application.properties` file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/blogdb
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

Adjust the database URL, username, and password according to your setup.

### Building and Running

1. Clone the repository
2. Navigate to the project directory
3. Build the project:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080` by default.

## Usage Examples

### Creating a new user

```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"John","email":"john@example.com","age":30,"password":"password123"}'
```

### Creating a new blog

```bash
curl -X POST http://localhost:8080/api/blogs \
  -H "Content-Type: application/json" \
  -d '{"title":"My First Blog","description":"This is my first blog post","user":{"id":1}}'
```

## Future Improvements

- Add user authentication and authorization
- Implement update operations for both entities
- Add pagination for list endpoints
- Add search functionality
- Implement comment feature for blog posts

## License

This project is licensed under the MIT License - see the LICENSE file for details.