package com.library.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(path= {"/", "/accueil"})
    public String accueil (Model model) {
        return "accueil";
    }
}
