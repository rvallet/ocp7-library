package com.library.mslibrary.service;

import com.library.mslibrary.entities.BookLoan;

import java.util.List;

public interface BookLoanService {

    List<BookLoan> findAll();
    BookLoan findBookLoanById (Long id);
    List<BookLoan> findBookLoansByUserId (Long id);
    BookLoan saveBookLoan (BookLoan bookLoan);
    BookLoan extendBookLoan (Long bookLoanId);
    List<BookLoan> saveAll (List<BookLoan> bookLoanList);

}
