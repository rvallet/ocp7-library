package com.library.mslibrary.ws.controller;

import com.library.mslibrary.api.ApiRegistration;
import com.library.mslibrary.config.ApplicationPropertiesConfig;
import com.library.mslibrary.entities.Book;
import com.library.mslibrary.service.BookService;
import com.library.mslibrary.ws.exception.NoSuchResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService bookService;

    @Autowired
    private ApplicationPropertiesConfig applicationPropertiesConfig;

    @GetMapping(value= ApiRegistration.REST_BOOKS_LIST)
    public List<Book> getBookList() throws NoSuchResultException {
        LOGGER.debug("getBookList from BookService");
        List<Book> bookList = bookService.findAll();
        LOGGER.info("Envoi d'une liste de {} livres", bookList.size());
        if (bookList.isEmpty()) throw new NoSuchResultException("Aucun Livre");
        //TODO : return pageable with properties
        return bookList;
    }

    @GetMapping(value= ApiRegistration.REST_GET_SEARCH_CRITERIA_LIST)
    public List<String> getSearchCriteriaList(){
        return bookService.getSearchCriteriaList();
    }

}
