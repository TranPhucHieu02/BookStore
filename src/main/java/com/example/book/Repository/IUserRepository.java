package com.example.book.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.book.Model.User;

public interface IUserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u From User u WHERE u.username =?1")
    User findByUsername(String username);
}
