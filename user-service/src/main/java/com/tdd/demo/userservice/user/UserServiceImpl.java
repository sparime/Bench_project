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
    public int saveUser(SUser user) {
        try{
            userRepository.save(user);
        }
        catch (Exception e){
            throw new RuntimeException("User not saved");
        }
        return user.getId();
    }

    @Override
    public List<SUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public int deleteUser(int userId) {
        try {
            userRepository.deleteById(userId);
        } catch (Exception e) {
            throw new RuntimeException(" User does not exist");
        }
        return userId;
    }

    @Override
    public SUser updateUserName(SUser user, String updatedName) {
        try {
            user.setUsername(updatedName);
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("user cannot be found");
        }
        return user;
    }

    @Override
    public boolean exists(SUser user) {
        return userRepository.existsById(user.getId());
    }


}
