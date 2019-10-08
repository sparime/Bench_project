package com.tdd.demo.userservice.user;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tdd.demo.userservice.PostServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    // need to have a DAO and JPA repo to autowire and run tests
    @Autowired
    private UserService userService;
    @Autowired
    private PostServiceProxy proxy;

    // gets
    @HystrixCommand(fallbackMethod = "fallbackdefaultFallbackMethod")
    @GetMapping("/users-hystrix-demo")
    public SUser getAllUsersHystrixDemo() {
        throw new RuntimeException("Hystrix demo");
    }


    @GetMapping("/users")
    public List<SUser> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{userName}")
    public SUser getByUserName(@PathVariable String userName) {
        return userService.getUserByName(userName);
    }

    //delete
    @DeleteMapping("/users/{userId}")
    public void delUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }

    // post mapping
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody SUser user) {

        int userId = userService.saveUser(user);

        // custom URL and redirection
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{username}").
                buildAndExpand(user.getUsername()).toUri();

        return ResponseEntity.created(location).build();

    }

    // updates - put and patch
    @PutMapping("/users")
    public ResponseEntity<Object> updateUser(@RequestBody SUser user) {

        SUser updated = userService.updateUserName(user, user.getUsername());

        // custom URL and redirection
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{username}").
                buildAndExpand(updated.getUsername()).toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping("/users/{userId}/posts/")
    public List<PostBean> getAllPostsOfAllUser(@PathVariable("userId") int userId) {
        List<PostBean> response = proxy.getAllPostsOfAllUser(userId);
        return response;
    }

    // customize this method to handle different scenarios
    public SUser fallbackdefaultFallbackMethod() {
        return new SUser("Ghost User - Service unavailable");
    }
}
