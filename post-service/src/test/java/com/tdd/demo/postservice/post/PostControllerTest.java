package com.tdd.demo.postservice.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PostController.class)
@AutoConfigureMockMvc
public class PostControllerTest {

    @MockBean
    private PostService postService;
    //@MockBean
    //private PostRepository postRepository;

    @Autowired
    private MockMvc mockmvc;

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
        //given(postService.findById(post.getPostId())).willReturn(post);
        given(postService.findById(post.getPostId())).willReturn(post);
        when(postService.findByContent("Service layer test post")).thenReturn(post);
        when(postService.savePost(post)).thenReturn(post.getPostId());
        when(postService.exists(post.getPostId())).thenReturn(true);

    }

    @TestConfiguration
    static class PostControllerTestContextConfiguration {

        // return this bean if service layer is called
        @Bean
        public PostService userService() {
            return new PostServiceImpl();
        }

    }

    @Test
    public void dummy_test() {
        // hard coded values for the dummy test to check mocking
        assertThat(postService.findById(12).getUserId()).isEqualTo(10);

    }

    // get mapping
    @Test
    public void getPostById_thenReturnThePost() throws Exception {
        Post post = new Post(12, 10, "Service layer test post");
        when(postService.findById(post.getPostId())).thenReturn(post);

        mockmvc.perform(get("/posts/{id}", 12).
                contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.*", hasSize(3))).
                andExpect(jsonPath("$.postId", is(12))).
                andExpect(jsonPath("$.userId", is(10))).
                andExpect(jsonPath("$.postContent", is("Service layer test post")));

    }

    // delete mapping test cases

}
