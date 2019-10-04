package com.tdd.demo.postservice.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post findById(int id) {
        Post fromDb;
        try {
            fromDb = postRepository.findPostByPostId(id);
        } catch (Exception e) {
            throw new RuntimeException("user not present");
        }
        return fromDb;
    }

    @Override
    public int savePost(Post post) {
        try {
            postRepository.save(post);
        } catch (Exception e) {
            throw new RuntimeException("Could not be saved");
        }
        return post.getPostId();
    }

    @Override
    public int updatePost(Post post) {
        try {
            postRepository.saveAndFlush(post);
            return postRepository.findPostByPostId(post.getPostId()).getPostId();
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

    @Override
    public Post findByContent(String content) {
        try {
            return postRepository.findPostByPostContent(content);
        } catch (Exception e) {
            throw new RuntimeException("user not present");
        }

    }

    @Override
    public boolean exists(int postId) {
        return postRepository.existsById(postId);
    }
}
