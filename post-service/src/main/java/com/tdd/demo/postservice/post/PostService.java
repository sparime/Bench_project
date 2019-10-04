package com.tdd.demo.postservice.post;

//@Service
public interface PostService {
    // add methods here
    Post findById(int id);

    int savePost(Post post);

    int updatePost(Post post);

    int deletePost(int id);

    Post findByContent(String content);

    boolean exists(int postId);
}
