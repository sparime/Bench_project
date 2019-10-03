package com.tdd.demo.userservice.user;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<SUser, Integer> {

    SUser findSUserByUsername(String username);

    @Override
    <S extends SUser> boolean exists(Example<S> example);

    Boolean exists(SUser user);


}
