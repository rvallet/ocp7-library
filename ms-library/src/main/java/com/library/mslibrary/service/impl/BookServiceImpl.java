package com.library.mslibrary.service.impl;

import com.library.mslibrary.entities.Book;
import com.library.mslibrary.enumerated.SearchCriteriaEnum;
import com.library.mslibrary.repository.BookRepository;
import com.library.mslibrary.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll(Sort.by("title"));
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findBookById(id);
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> saveAll(List<Book> bookList) {
        return bookRepository.saveAll(bookList);
    }

    @Override
    public List<Book> findBooksWithCriteria(String searchCriteria, String searchValue) {
        List<Book> result = new ArrayList<>();

        if(Stream.of(SearchCriteriaEnum.values()).anyMatch(v -> v.toString().equalsIgnoreCase(searchCriteria))){
            switch (searchCriteria) {
                case "author":
                    result = bookRepository.findBookByAuthor(searchValue.trim().toLowerCase());
                    break;
                case "title":
                    result = bookRepository.findBookByTitle(searchValue.trim().toLowerCase());
                    break;
            }
    }
        return result;
    }
}
