package com.library.mslibrary.service;

import com.library.mslibrary.entities.BookLoan;

import java.util.List;

public interface BookLoanService {

    List<BookLoan> findAll();
    BookLoan findBookLoanById (Long id);
    BookLoan saveBookLoan (BookLoan bookLoan);
    List<BookLoan> saveAll (List<BookLoan> bookLoanList);

}
