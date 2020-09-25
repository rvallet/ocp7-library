package com.library.mslibrary.repository;

import com.library.mslibrary.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findAll();
    Book findBookById(Long id);


}
