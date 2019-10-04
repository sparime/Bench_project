package com.tdd.demo.postservice.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(PostController.class)
@AutoConfigureMockMvc
public class PostControllerTest {

    @MockBean
    private PostService postService;
    //@MockBean
    //private PostRepository postRepository;

    @Autowired
    private MockMvc mockMvc;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setUp() {
        Post post = new Post(12, 10, "Service layer test post");
        // mock all repo calls here
        Mockito.when(postService.findById(post.getPostId())).thenReturn(post);
        Mockito.when(postService.findByContent("Service layer test post")).thenReturn(post);
        Mockito.when(postService.savePost(post)).thenReturn(post.getPostId());
        Mockito.when(postService.exists(post.getPostId())).thenReturn(true);

    }

    @Test
    public void dummy_test() {
        assertThat(postService.findById(12).getUserId()).isEqualTo(10);
    }

    @TestConfiguration
    static class PostControllerTestContextConfiguration {

        // return this bean if service layer is called
        @Bean
        public PostService userService() {
            return new PostServiceImpl();
        }

    }


}
