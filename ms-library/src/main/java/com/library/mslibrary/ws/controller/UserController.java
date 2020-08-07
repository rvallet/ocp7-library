package com.library.mslibrary.ws.controller;

import com.library.mslibrary.api.ApiRegistration;
import com.library.mslibrary.service.UserService;
import com.library.mslibrary.entities.User;
import com.library.mslibrary.ws.exception.NoSuchResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value= ApiRegistration.REST_USERS)
    public List<User> getUsers() throws NoSuchResultException {
        List<User> userList = userService.findAll();
        if (userList.isEmpty()) throw new NoSuchResultException("Aucun Utilisateur");
        return userList;
    }
}
