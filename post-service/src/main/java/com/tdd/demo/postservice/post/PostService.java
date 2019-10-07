package com.tdd.demo.postservice.post;

import java.util.List;

//@Service
public interface PostService {
    // add methods here
    Post findById(int id);

    int savePost(Post post);

    int updatePost(Post post);

    int deletePost(int id);

    Post findByContent(String content);

    boolean exists(int postId);

    List<Post> findAllByUserId(int userId);
}
