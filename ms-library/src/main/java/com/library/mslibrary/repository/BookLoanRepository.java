package com.library.mslibrary.repository;

import com.library.mslibrary.entities.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookLoanRepository extends JpaRepository<BookLoan, String> {

    BookLoan findBookLoanById (Long id);

}
