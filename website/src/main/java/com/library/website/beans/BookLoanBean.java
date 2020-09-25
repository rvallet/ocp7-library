package com.library.website.beans;

import java.util.Date;

public class BookLoanBean {

    private Long id;

    private Date startLoan;

    private Date endLoan;

    private Boolean isLoanExtended;

    private String loanStatus;

    private UserBean user;

    private BookBean book;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartLoan() {
        return startLoan;
    }

    public void setStartLoan(Date startLoan) {
        this.startLoan = startLoan;
    }

    public Date getEndLoan() {
        return endLoan;
    }

    public void setEndLoan(Date endLoan) {
        this.endLoan = endLoan;
    }

    public Boolean getLoanExtended() {
        return isLoanExtended;
    }

    public void setLoanExtended(Boolean loanExtended) {
        isLoanExtended = loanExtended;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public BookBean getBook() {
        return book;
    }

    public void setBook(BookBean book) {
        this.book = book;
    }
}
