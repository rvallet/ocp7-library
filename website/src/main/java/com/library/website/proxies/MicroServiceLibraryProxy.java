package com.library.website.proxies;

import com.library.website.beans.UserBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "ms-library", url="localhost:9001")
public interface MicroServiceLibraryProxy {

    @GetMapping(value= "/users")
    List<UserBean> getUsers();
}
