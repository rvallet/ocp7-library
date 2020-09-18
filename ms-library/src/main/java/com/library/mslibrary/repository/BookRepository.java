package com.library.mslibrary.repository;

import com.library.mslibrary.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {

    Book findBookById(Long id);

}
