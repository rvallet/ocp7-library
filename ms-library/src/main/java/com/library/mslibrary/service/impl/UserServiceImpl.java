package com.library.mslibrary.service.impl;

import com.library.mslibrary.entities.User;
import com.library.mslibrary.repository.UserRepository;
import com.library.mslibrary.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        userRepository.save(user);
        LOGGER.info("Utilisateur {} enregistré", user.getEmail());
        return user;
    }

    @Override
    public List<User> saveAll(List<User> userList) {
        return userRepository.saveAll(userList);
    }

}
