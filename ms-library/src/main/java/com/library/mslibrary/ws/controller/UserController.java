package com.library.mslibrary.ws.controller;

import com.library.mslibrary.api.ApiRegistration;
import com.library.mslibrary.config.ApplicationPropertiesConfig;
import com.library.mslibrary.service.UserService;
import com.library.mslibrary.entities.User;
import com.library.mslibrary.ws.exception.NoSuchResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationPropertiesConfig applicationPropertiesConfig;

    @GetMapping(value= ApiRegistration.REST_USERS)
    public List<User> getUsers() throws NoSuchResultException {
        List<User> userList = userService.findAll();
        if (userList.isEmpty()) throw new NoSuchResultException("Aucun Utilisateur");
        //TODO : return pageable with properties
        LOGGER.debug("PageSizeLimit = {}", applicationPropertiesConfig.getPageSizeLimit());
        return userList;
    }

    @GetMapping(value= ApiRegistration.REST_GET_USER_BY_EMAIL + "/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) throws NoSuchResultException {
        Optional<User> user = Optional.ofNullable(userService.findUserByEmail(email));
        return user;
    }

    @PostMapping(value = ApiRegistration.REST_SAVE_USER)
    public void saveUser(@RequestBody User user) {
        if (user==null) throw new NoSuchResultException("Demande d'enregistrement utilisateur : ECHEC");
        userService.saveUser(user);
    }



}
