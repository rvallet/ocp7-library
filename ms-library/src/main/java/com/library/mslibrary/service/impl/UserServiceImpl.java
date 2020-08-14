package com.library.mslibrary.service.impl;

import com.library.mslibrary.entities.User;
import com.library.mslibrary.repository.UserRepository;
import com.library.mslibrary.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
    public User findById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LOGGER.info("loadUserByUsername START with email = {}", email);
        User user = userRepository.findUserByEmail(email);

        if (user == null){
            LOGGER.warn("loadUserByUsername FAILED");
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        LOGGER.info("Found - loadUserByUsername with {} {} {}",user.getEmail(),user.getPassword(),user.getRole());
        org.springframework.security.core.userdetails.User uControl = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                convertRoleEnumToAuthorities(user.getRole()));
        LOGGER.info("Found - loadUserByUsername with {}",uControl.toString());
        return uControl;
    }

    private Collection<? extends GrantedAuthority> convertRoleEnumToAuthorities(String role){
        return AuthorityUtils.createAuthorityList(role);
    }


}
