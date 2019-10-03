package com.tdd.demo.userservice.user;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

// JPA test class
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {

    // entity manager to setup basic H2 config stuff
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByName_thenReturnUser(){
        SUser user = new SUser("test_user");
        entityManager.persist(user);
        entityManager.flush();

        SUser found = userRepository.findSUserByUsername(user.getUsername());
        // then
        assertThat(found.getUsername()).isEqualTo(user.getUsername());

    }

//    @Test
//    public void findSUerById_thenReturnUser(){
//        SUser user = new SUser("test_user_id");
//        user.setId(999);
//        entityManager.persist(user);
//        entityManager.flush();
//
//        Optional<SUser> found = userRepository.findById(999);
//        assertThat(found).isNotNull();
//
//    }


}
