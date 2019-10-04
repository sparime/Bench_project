package com.tdd.demo.postservice.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findPostByPostContent(String post_content);

    Post findPostByPostId(int id);

    Post save(Post post);
}
