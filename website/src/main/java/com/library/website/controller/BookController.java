package com.library.website.controller;

import com.library.website.beans.BookBean;
import com.library.website.proxies.MicroServiceLibraryProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @Autowired
    MicroServiceLibraryProxy msLibraryProxy;

    @GetMapping("/livres")
    public String bookList (
            @RequestParam(name="searchCriteria", required = false) String searchCriteria,
            @RequestParam(name="searchValue", required = false) String searchValue,
            Model model) {

        LOGGER.debug("Envoie d'un demande de listes de livres");
        List<BookBean> bookList = msLibraryProxy.getBookList();
        List<String> searchCriteriaList = msLibraryProxy.getSearchCriteriaList();

        if (searchCriteria != null && searchValue != null) {
            LOGGER.debug("Recherche de livres de type {} contient '{}'", searchCriteria, searchValue);
            if (searchCriteriaList != null) {
                if (searchCriteria.trim().toLowerCase().equalsIgnoreCase(searchCriteriaList.get(0))) {
                    bookList = bookList.stream()
                            .filter(b -> b.getAuthor().toLowerCase().contains(searchValue.trim().toLowerCase()))
                            .collect(Collectors.toList());
                }
                if (searchCriteria.trim().toLowerCase().equalsIgnoreCase(searchCriteriaList.get(1))) {
                    bookList = bookList.stream()
                            .filter(b -> b.getTitle().toLowerCase().contains(searchValue.trim().toLowerCase()))
                            .collect(Collectors.toList());
                }

                if (searchCriteria.trim().toLowerCase().equalsIgnoreCase(searchCriteriaList.get(2))) {
                    bookList = bookList.stream()
                            .filter(b -> b.getCollection().toLowerCase().contains(searchValue.trim().toLowerCase()))
                            .collect(Collectors.toList());
                }

                model.addAttribute("searchCriteria", searchCriteria);
                model.addAttribute("searchValue", searchValue);
            }
        }

        LOGGER.info("Réception d'une liste de {} livres.", bookList.size());
        model.addAttribute("bookList", bookList);
        model.addAttribute("searchCriteriaList", searchCriteriaList );
        return "livres";
    }
}
