package com.tdd.demo.postservice.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable int id) {
        return postService.findById(id);
    }
}
