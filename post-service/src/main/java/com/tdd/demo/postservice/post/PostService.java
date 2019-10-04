package com.tdd.demo.postservice.post;

import org.springframework.stereotype.Service;

@Service
public interface PostService {
    // add methods here
    Post findById(int id);

    int savePost(Post post);

    int updatePost(Post post);

    int deletePost(int id);
}
