package com.library.website.controller;

import com.library.website.beans.BookLoanBean;
import com.library.website.beans.UserBean;
import com.library.website.proxies.MicroServiceLibraryProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfilController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfilController.class);

    @Autowired
    MicroServiceLibraryProxy msLibraryProxy;

    @GetMapping("/user/profil")
    public String userProfil(Model model) {
        UserBean u = msLibraryProxy.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user" , u );

        List<BookLoanBean> bookLoanList = new ArrayList<>();
        model.addAttribute("bookLoanList" , bookLoanList );

        LOGGER.info("Chargment du profil {}", u.getEmail());
        return "user/profil";
    }
}
