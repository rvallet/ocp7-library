package com.library.website.controller;

import com.library.website.beans.UserBean;
import com.library.website.proxies.MicroServiceLibraryProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @GetMapping(path="/login")
    public String login(){
        return "login";
    }

    @GetMapping(value="/logout")
    public String logout (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

}
