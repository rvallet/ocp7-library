package com.library.website.controller;

import com.library.website.beans.BookBean;
import com.library.website.proxies.MicroServiceLibraryProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @Autowired
    MicroServiceLibraryProxy msLibraryProxy;

    @GetMapping("/livres")
    public String bookList (Model model) {
        LOGGER.debug("Envoie d'un demande de listes de livres");
        List<BookBean> bookList = msLibraryProxy.getBookList();
        LOGGER.info("Réception d'une liste de {} livres.", bookList.size());
        model.addAttribute("bookList" , bookList );
        return "livres";
    }
}
