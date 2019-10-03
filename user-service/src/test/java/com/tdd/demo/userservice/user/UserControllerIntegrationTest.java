package com.tdd.demo.userservice.user;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
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

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @TestConfiguration
    static class UserControllerTestContextConfiguration {

        // return this bean if service layer is called
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
       }
   }

    /* Get mapping test cases */
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

    /* Post Mapping test cases*/
    @Test
    public void saveAUser_thenReturnTheSavedUser()throws Exception{

        SUser user = new SUser("Control_test");
        when(userRepository.exists(user)).thenReturn(false);

        // this works only when when(mocked bean). some void method
        //doNothing().doThrow(new RuntimeException()).when(userRepository).save(user);

        mockmvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("http://localhost/users")));

        // check for cleanup calls - this only works when userService is a mockbean. will add support for this later
        //verify(userService, times(1)).exists(user);
        //verify(userService, times(1)).saveUser(user);
        //verifyNoMoreInteractions(userService);

    }

    /* Delete mapping test cases*/


    /* Update mapping tset cases*/
}
