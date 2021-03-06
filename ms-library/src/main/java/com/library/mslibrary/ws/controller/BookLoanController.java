package com.library.mslibrary.ws.controller;

import com.library.mslibrary.api.ApiRegistration;
import com.library.mslibrary.config.ApplicationPropertiesConfig;
import com.library.mslibrary.entities.Book;
import com.library.mslibrary.entities.BookLoan;
import com.library.mslibrary.entities.User;
import com.library.mslibrary.enumerated.BookLoanStatusEnum;
import com.library.mslibrary.service.BookLoanService;
import com.library.mslibrary.service.BookService;
import com.library.mslibrary.utils.DateTools;
import com.library.mslibrary.ws.exception.NoSuchResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookLoanController {

    @Autowired
    private ApplicationPropertiesConfig appConfig;

    private static final Logger LOGGER = LoggerFactory.getLogger(BookLoanController.class);

    @Autowired
    BookLoanService bookLoanService;

    @Autowired
    BookService bookService;

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

    @GetMapping(value= ApiRegistration.REST_BOOK_LOANS_EXTEND + "/{bookLoanId}")
    public BookLoan extendBookLoan(@PathVariable Long bookLoanId) throws NoSuchResultException {
        BookLoan bl = bookLoanService.extendBookLoan(bookLoanId);
        LOGGER.info("Prolongation de l'emprunt bookLoanId = {}", bookLoanId);
        return bl;
    }

    @GetMapping(value= ApiRegistration.REST_BOOK_LOANS_CLOSE + "/{bookLoanId}")
    public BookLoan closeBookLoan(@PathVariable Long bookLoanId) throws NoSuchResultException {
        BookLoan bl = bookLoanService.closeBookLoan(bookLoanId);
        LOGGER.info("Clôture de l'emprunt bookLoanId = {}", bookLoanId);
        return bl;
    }

    @GetMapping(value= ApiRegistration.REST_GET_BOOK_LOAN_BY_ID + "/{bookLoanId}")
    public BookLoan findBookLoanById(@PathVariable Long bookLoanId) throws NoSuchResultException {
        BookLoan bl = bookLoanService.findBookLoanById(bookLoanId);
        LOGGER.info("Recherche de l'emprunt bookLoanId = {}", bookLoanId);
        return bl;
    }

    @PostMapping(value= ApiRegistration.REST_SAVE_BOOK_LOAN)
    public void saveBookLoan(@RequestBody BookLoan bookLoan) {
        if (bookLoan==null) throw new NoSuchResultException("Demande d'enregistrement d'emprunt : ECHEC");
        bookLoanService.saveBookLoan(bookLoan);
    }

    @PostMapping(value= ApiRegistration.REST_CREATE_BOOK_LOAN)
    public void createBookLoan(@RequestBody BookLoan bookLoan) {
        if (bookLoan==null || bookLoan.getBook()==null || bookLoan.getUser()==null) throw new NoSuchResultException("Demande d'enregistrement d'emprunt : ECHEC");

        BookLoan bookLoanToCreate = new BookLoan(bookLoan.getUser(), bookLoan.getBook(), appConfig.getBookLoanDuration());

        Book bookToUpdate = bookLoanToCreate.getBook();

        bookToUpdate.setStock(bookToUpdate.getStock()-1);
        if (bookToUpdate.getStock() <1){
            bookToUpdate.setLoanAvailable(false);
        }
        LOGGER.info("Création d'un emprunt (Ouvrage : {} - Usager : {}", bookLoanToCreate.getBook().getTitle(), bookLoanToCreate.getUser().getEmail());
        bookService.saveBook(bookToUpdate);
        bookLoanService.saveBookLoan(bookLoanToCreate);
    }
}

