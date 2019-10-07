package com.tdd.demo.postservice.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private Environment environment;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable int id) {
        return postService.findById(id);
    }

    @DeleteMapping("/posts/{id}")
    public int deletePost(@PathVariable int id) {
        return postService.deletePost(id);
    }

    @PostMapping("/posts")
    public ResponseEntity<Object> createPost(@RequestBody Post post) {
        int postId = postService.savePost(post);

        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{postId}").
                buildAndExpand(post.getPostId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/posts")
    public ResponseEntity<Object> updatePost(@RequestBody Post post) {
        int updatedPostId = postService.updatePost(post);

        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{postId}").
                buildAndExpand((updatedPostId)).toUri();

        ResponseEntity.status(HttpStatus.OK);
        return ResponseEntity.created(location).build();

    }

    @GetMapping("/users/{userId}/posts/")
    public List<Post> getAllPostsOfAllUser(@PathVariable("userId") int userId) {

        logger.info("{userID from user service ->}", userId);
        return postService.findAllByUserId(userId);

    }

}
