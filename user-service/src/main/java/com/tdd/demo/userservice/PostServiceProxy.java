package com.tdd.demo.userservice;

import com.tdd.demo.userservice.user.PostBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "post-service", url = "localhost:8080")
public interface PostServiceProxy {
    @GetMapping("/users/{userId}/posts/")
    List<PostBean> getAllPostsOfAllUser(@PathVariable("userId") int userId);
}
