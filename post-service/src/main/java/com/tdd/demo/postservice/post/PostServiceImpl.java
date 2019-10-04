package com.tdd.demo.postservice.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post findById(int id) {
        try {
            return postRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("user not present");
        }
    }

    @Override
    public int savePost(Post post) {
        try {
            Post saved = postRepository.saveAndFlush(post);
            return saved.getPostId();
        } catch (Exception e) {
            throw new RuntimeException("Could not be saved");
        }
    }

    @Override
    public int updatePost(Post post) {
        try {
            postRepository.saveAndFlush(post);
            return postRepository.findById(post.getPostId()).orElse(null).getPostId();
        } catch (Exception e) {
            throw new RuntimeException("Could not update");
        }
    }

    @Override
    public int deletePost(int id) {

        try {
            postRepository.deleteById(id);
            return id;
        } catch (Exception e) {
            throw new RuntimeException("Could not delete user");
        }
    }
}
