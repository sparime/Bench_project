package com.tdd.demo.postservice.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class PostServiceLayerIntegrationTest {

    // stubbed post and mocked postRepo to test postService

    @MockBean
    private PostRepository postRepository;
    @Autowired
    private PostService postService;

    @TestConfiguration
    static class PostServiceTestContextConfiguration {

        @Bean
        public PostService postService() {
            return new PostServiceImpl();
        }
    }
    // set up before and/or after
    @Before
    public void setUp() {
        Post post = new Post(12, 10, "Service layer test post");
        // mock all repo calls here
        Mockito.when(postRepository.findPostByPostId(post.getPostId())).thenReturn(post);
        Mockito.when(postRepository.findPostByPostContent("Service layer test post")).thenReturn(post);
        Mockito.when(postRepository.save(post)).thenReturn(post);
        Mockito.when(postRepository.existsById(post.getPostId())).thenReturn(true);

    }
    // tests
    @Test
    public void findById_thenReturnPost() {
        //Post post = new Post(12,10, "Service layer test post");
        Post post = new Post(12, 10, "Service layer test post");
        assertThat(postService.findById(post.getPostId()).getPostId()).isEqualTo(post.getPostId());
    }

    @Test
    public void findByContent_thenReturnPost() {
        //Post post = new Post(12,10, "Service layer test post");
        Post post = new Post(12, 10, "Service layer test post");
        assertThat(postService.findByContent(post.getPostContent()).getPostContent()).isEqualToIgnoringCase(post.getPostContent());
    }

    @Test
    public void savePost_thenReturnPost() {
        Post post = new Post(12, 10, "Service layer test post");
        assertThat(postService.savePost(post)).isEqualTo(post.getPostId());
    }

    @Test
    public void updatePost_thenReturnPostId() {
        // can write a better one
        Post post = new Post(12, 10, "Service layer test post updated");
        assertThat(postService.updatePost(post)).isEqualTo(post.getPostId());
    }

    @Test
    public void deletePost_thenReturnPostId() {
        Post post = new Post(12, 10, "Service layer test post");
        int id = postService.deletePost(post.getPostId());
        assertThat(post.getPostId()).isEqualTo(id);
    }

    @Test
    public void checkExists_thenReturnTrue() {
        Post post = new Post(12, 10, "Service layer test post");
        assertThat(postService.exists(post.getPostId())).isTrue();
    }
}
