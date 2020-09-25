package com.library.website.proxies;

import com.library.website.beans.BookBean;
import com.library.website.beans.BookLoanBean;
import com.library.website.beans.UserBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ms-library")
@RibbonClient(name = "ms-library")
public interface MicroServiceLibraryProxy {

    @GetMapping(value= "/users")
    List<UserBean> getUsers();

    @GetMapping(value= "/booksList")
    List<BookBean> getBookList();

    @GetMapping(value= "/user/{id}")
    UserBean getUserById(@PathVariable String id);

    @GetMapping(value= "/book/{id}")
    BookBean getBookById(@PathVariable String id);

    @GetMapping(value= "/bookLoan/{id}")
    BookLoanBean getBookLoanById(@PathVariable String id);

    @GetMapping(value= "/findBookLoansListByUserId/{userId}")
    List<BookLoanBean> getBookLoansByUserId(@PathVariable String userId);

    @GetMapping(value= "/getBookLoansList")
    List<BookLoanBean> getBookLoansList();

    @GetMapping(value = "/userDetails")
    UserDetails getUserDetails() ;

    @GetMapping(value = "/findUserByEmail/{email}")
    UserBean getUserByEmail(@PathVariable String email);

    @PostMapping(value = "/saveUser")
    UserBean saveUser(@RequestBody UserBean user);

    @PostMapping(value = "/saveBook")
    BookBean saveBook(@RequestBody BookBean book);

    @PostMapping(value = "/saveBookLoan")
    BookLoanBean saveBookLoan(@RequestBody BookLoanBean bookLoanBean);


}
