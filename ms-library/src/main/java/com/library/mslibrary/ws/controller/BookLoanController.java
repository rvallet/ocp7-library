package com.library.mslibrary.ws.controller;

import com.library.mslibrary.api.ApiRegistration;
import com.library.mslibrary.config.ApplicationPropertiesConfig;
import com.library.mslibrary.entities.Book;
import com.library.mslibrary.entities.BookLoan;
import com.library.mslibrary.service.BookLoanService;
import com.library.mslibrary.ws.exception.NoSuchResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookLoanController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookLoanController.class);

    @Autowired
    BookLoanService bookLoanService;

    @Autowired
    private ApplicationPropertiesConfig applicationPropertiesConfig;

    @GetMapping(value= ApiRegistration.REST_BOOK_LOANS_LIST_BY_USER_ID + "/{userId}")
    public List<BookLoan> findBookLoansListByUserId(@PathVariable String userId) throws NoSuchResultException {
        LOGGER.debug("findBookLoansListByUserId for userId = {}", userId);
        List<BookLoan> bookLoanList = bookLoanService.findBookLoansByUserId(Long.parseLong(userId));
        LOGGER.info("Envoi d'une liste de {} emprunts", bookLoanList.size());
            //TODO : return pageable with properties
            LOGGER.debug("PageSizeLimit = {}", applicationPropertiesConfig.getPageSizeLimit());
            return bookLoanList;
    }

    @GetMapping(value= ApiRegistration.REST_BOOK_LOANS_LIST)
    public List<BookLoan> findBookLoansList() throws NoSuchResultException {
        List<BookLoan> bookLoanList = bookLoanService.findAll();
        LOGGER.info("Envoi d'une liste de {} emprunts", bookLoanList.size());
        //TODO : return pageable with properties
        LOGGER.debug("PageSizeLimit = {}", applicationPropertiesConfig.getPageSizeLimit());
        return bookLoanList;
    }

}

