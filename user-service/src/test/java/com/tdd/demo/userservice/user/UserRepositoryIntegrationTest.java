package com.tdd.demo.userservice.user;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

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

    @Test
    public void saveUser_thenReturnGeneratedId() {
        SUser user = new SUser("save user test");
        entityManager.persistAndFlush(user);

        SUser found = userRepository.findSUserByUsername(user.getUsername());
        // then
        assertThat(found.getUsername()).isEqualTo(user.getUsername());

    }

    @Test
    public void deleteUser_thenReturnDeletedUserId() {
        SUser user = new SUser("To be deleted");
        entityManager.persistAndFlush(user);

        SUser found = userRepository.findSUserByUsername(user.getUsername());
        // now delete
        userRepository.deleteById(found.getId());
        assertThat(userRepository.findSUserByUsername(user.getUsername())).isNull();

    }

    @Test
    public void updateUsername_thenReturnTheUpdatedUserObject() {
        // 10 is a hardcoded test case value
        Optional<SUser> found = userRepository.findById(10);
        SUser fromDb = found.orElse(null);

        // update
        if (fromDb != null) {
            fromDb.setUsername("Updated name");
        }

        entityManager.persistAndFlush(fromDb);
        assertThat(fromDb.getId()).isEqualTo(10);
    }


}
