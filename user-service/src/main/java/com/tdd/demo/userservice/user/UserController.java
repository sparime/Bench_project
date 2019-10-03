package com.tdd.demo.userservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    // need to have a DAO and JPA repo to autowire and run tests
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<SUser> getAllUsers(){
        return userService.getAllUsers();
    }


}
