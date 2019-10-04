package com.tdd.demo.postservice.post;

import org.springframework.stereotype.Component;

@Component
public class PostServiceImpl implements PostService {
    @Override
    public Post findById(int id) {
        return null;
    }

    @Override
    public int savePost(Post post) {
        return 0;
    }

    @Override
    public int updatePost(Post post) {
        return 0;
    }
}
