package com.tdd.demo.postservice.post;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class PostServiceLayerIntegrationTest {

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

    // tests

}
