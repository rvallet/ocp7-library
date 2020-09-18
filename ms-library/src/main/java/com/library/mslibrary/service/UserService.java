package com.library.mslibrary.service;

import com.library.mslibrary.entities.User;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findUserById (Long id);
    User findUserByEmail (String email);
    User saveUser(User user);
    List<User> saveAll(List<User> userList);

}
