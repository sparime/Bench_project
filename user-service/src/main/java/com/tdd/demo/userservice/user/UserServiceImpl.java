package com.tdd.demo.userservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public SUser getUserByName(String username) {
        return userRepository.findSUserByUsername(username);
    }

    @Override
    public SUser saveUser(SUser user) {
        try{
            userRepository.save(user);
        }
        catch (Exception e){
            throw new RuntimeException("User not saved");
        }
        return user;
    }

    @Override
    public List<SUser> getAllUsers() {
        return userRepository.findAll();
    }

}
