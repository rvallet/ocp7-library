package com.library.mslibrary.api;

public interface ApiRegistration {

    String SERVICE_ID = "LIBRARY";

    String REST_PREFIX = "/ms-library";

    String REST_USERS = "/users";

    String REST_GET_USER_BY_EMAIL = "/findUserByEmail";

    String REST_SAVE_USER = "/saveUser";

    String REST_BOOKS_LIST = "/booksList";

    String REST_BOOK_BY_ID = "/findBookById";

    String REST_BOOK_LOANS_LIST = "/getBookLoansList";

    String REST_BOOK_LOANS_LIST_BY_USER_ID = "/findBookLoansListByUserId";

    String REST_BOOKS_LOANS = "/booksLoans";

    String REST_GET_BOOK_BY_ID = "/findBookById";

    String REST_PAGINATION = "/page";

    String REST_BOOK_LOANS_EXTEND = "/extendBookLoan";

    String REST_BOOK_LOANS_CLOSE = "/closeBookLoan";

    String REST_GET_BOOK_LOAN_BY_ID = "/bookLoan";
}
