package com.library.mslibrary.service;

import com.library.mslibrary.entities.User;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> findAll();
    User findById (Long id);
    User findByEmail (String email);

}
