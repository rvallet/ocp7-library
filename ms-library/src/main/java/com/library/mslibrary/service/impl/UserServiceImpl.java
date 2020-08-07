package com.library.mslibrary.service.impl;

import com.library.mslibrary.entities.User;
import com.library.mslibrary.repository.UserRepository;
import com.library.mslibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
