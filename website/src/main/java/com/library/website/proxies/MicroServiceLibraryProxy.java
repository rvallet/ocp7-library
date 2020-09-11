package com.library.website.proxies;

import com.library.website.beans.UserBean;
import com.library.website.service.UserService;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-library")
@RibbonClient(name = "ms-library")
public interface MicroServiceLibraryProxy {

    @GetMapping(value= "/users")
    List<UserBean> getUsers();

    @GetMapping(value = "/userDetails")
    UserDetails getUserDetails() ;

    @GetMapping(value = "/findUserByEmail/{email}")
    UserBean getUserByEmail(@PathVariable String email) ;

}
