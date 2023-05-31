package com.example.book.Model;

import java.util.ArrayList;
import java.util.List;

import com.example.book.Validator.annotation.ValidUsername;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length=50,nullable = false, unique = true)
    @NotEmpty(message = "username không được để trống")
    @Size(max = 50, message = "Tên không vượt quá 50 ký tự")
    @ValidUsername
    private String username;

    @Column(name = "password",length = 205, nullable = false)
    @NotEmpty(message = "password không được để trống")
    private String password;

    @Column(name = "email" ,length = 50)
    @NotNull(message = "email không được để trống")
    @Size(max = 50, message = "email không vượt quá 50 ký tự")
    private String email;
    
    @Column(name = "name" ,nullable = false)
    @NotNull(message = "name không được để trống")
    private String name;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Book> books =new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public User() {
    }

    public User(Long id,
            @NotEmpty(message = "username không được để trống") @Size(max = 50, message = "Tên không vượt quá 50 ký tự") String username,
            @NotEmpty(message = "password không được để trống") String password,
            @NotNull(message = "email không được để trống") @Size(max = 50, message = "email không vượt quá 50 ký tự") String email,
            @NotNull(message = "name không được để trống") String name, List<Book> books) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.books = books;
    }
    
}
