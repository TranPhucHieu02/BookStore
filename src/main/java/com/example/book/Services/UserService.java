package com.example.book.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.book.Model.User;
import com.example.book.Repository.IUserRepository;

@Service
public class UserService {
    @Autowired IUserRepository userRepository;
        public void save(User user){
            userRepository.save(user);
        }
}
