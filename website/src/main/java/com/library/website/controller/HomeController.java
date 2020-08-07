package com.library.website.controller;

import com.library.website.beans.UserBean;
import com.library.website.proxies.MicroServiceLibraryProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    MicroServiceLibraryProxy msLibraryProxy;

    @GetMapping(path= {"/", "/accueil"})
    public String accueil (Model model) {
        List<UserBean> userList = msLibraryProxy.getUsers();
        model.addAttribute("userList", userList);
        return "accueil";
    }
}
