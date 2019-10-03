package com.tdd.demo.userservice.user;

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
public class UserServiceLayerIntegrationTest {

    // context configuration
    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {

        // return this bean if service layer is called
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    // get the user service layer
    @Autowired
    private UserService userService;
    // mockito mock bean - to bypass the actual userRepo bean, returns what is needed based on the setup
    @MockBean
    private UserRepository userRepository;
    // before testing setup
    @Before
    public  void setUp(){
        SUser user = new SUser("Service layer testing user");
        Mockito.when(userRepository.findSUserByUsername(user.getUsername())).thenReturn(user);
    }

    @Test
    public void whenValidName_thenUserShouldBeFound(){
        String name = "Service layer testing user";
        SUser found = userService.getUserByName(name);

        assertThat(found.getUsername()).isEqualTo(name);
    }


    @Test
    public void whenSaved_thenReturnId() {

        SUser user = new SUser("user save");
        user.setId(101);

        int id = userService.saveUser(user);
        assertThat(id).isEqualTo(101);


    }

}
