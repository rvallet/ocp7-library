package com.library.mslibrary.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name="book", uniqueConstraints = @UniqueConstraint(columnNames = "isbn"))
public class Book implements Serializable {

    @Id
    @Column(name="id_book")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Size(min = 1, max = 1000)
    private String description;

    private String shortDescription;

    private String author;

    private String editor;

    private String collection;

    private String isbn;

    private String imgPathThAttribute;

    private Date releaseDate;

    private int stock;

    private Boolean isLoanAvailable;

    private Boolean isOnline;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<BookLoan> bookLoan;

    public Book() {
        super();
        this.isOnline = false;
        this.isLoanAvailable = false;
    }

    public Book(String title, String description, String author, String editor, String collection, String isbn, Date releaseDate) {
        this.title = title;
        this.description = description;
        this.setShortDescription(description);
        this.author = author;
        this.editor = editor;
        this.collection = collection;
        this.isbn = isbn;
        this.releaseDate = releaseDate;
        this.isOnline = false;
        this.isLoanAvailable = this.stock > 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription.length() > 75 ? shortDescription.substring(0, 72)+"...": shortDescription;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getImgPathThAttribute() {
        return imgPathThAttribute;
    }

    public void setImgPathThAttribute(String imgPathThAttribute) {
        this.imgPathThAttribute = imgPathThAttribute;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public Collection<BookLoan> getBookLoan() {
        return bookLoan;
    }

    public void setBookLoan(Collection<BookLoan> bookLoan) {
        this.bookLoan = bookLoan;
    }

    public Boolean getLoanAvailable() {
        return isLoanAvailable;
    }

    public void setLoanAvailable(Boolean loanAvailable) {
        isLoanAvailable = loanAvailable;
    }
}
