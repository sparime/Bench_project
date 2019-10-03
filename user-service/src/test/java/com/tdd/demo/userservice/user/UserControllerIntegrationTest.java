package com.tdd.demo.userservice.user;

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

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
// mvc testing - class  being tested for
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerIntegrationTest   {
    @Autowired
    private MockMvc mockmvc;
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;
    @TestConfiguration
    static class UserControllerTestContextConfiguration {

        // return this bean if service layer is called
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
       }
   }

    @Test
    public void givenUsers_whenUsers_returnAllUsersJsonArray() throws Exception{
        SUser user = new SUser("test_user");
        List<SUser> allUsers = Arrays.asList(user);

        given(userService.getAllUsers()).willReturn(allUsers);
        mockmvc.perform(get("/users").
                contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.*", hasSize(1)));
    }

    /*@Test
    public void saveAUser_thenReturnTheSavedUser()throws Exception{

        when(userService.saveUser(any(SUser.class))).thenReturn(new SUser("posted_user"));

        mockmvc.perform(post("users").
                contentType(MediaType.APPLICATION_JSON).
                content("{ username: posted_user} ").
                accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isCreated()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).
                andExpect(header().string("Location", "/users/posted_user")).
                andExpect(jsonPath("$"))


    }*/


}
