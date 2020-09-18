package com.library.mslibrary.service.impl;

import com.library.mslibrary.entities.BookLoan;
import com.library.mslibrary.repository.BookLoanRepository;
import com.library.mslibrary.service.BookLoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookLoanServiceImpl implements BookLoanService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookLoanServiceImpl.class);

    @Autowired
    BookLoanRepository bookLoanRepository;

    @Override
    public List<BookLoan> findAll() {
        return bookLoanRepository.findAll();
    }

    @Override
    public BookLoan findBookLoanById(Long id) {
        return bookLoanRepository.findBookLoanById(id);
    }

    @Override
    public BookLoan saveBookLoan(BookLoan bookLoan) {
        return bookLoanRepository.save(bookLoan);
    }

    @Override
    public List<BookLoan> saveAll(List<BookLoan> bookLoanList) {
        return bookLoanRepository.saveAll(bookLoanList);
    }
}
