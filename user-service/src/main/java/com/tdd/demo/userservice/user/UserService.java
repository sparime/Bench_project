package com.tdd.demo.userservice.user;

import java.util.List;

public interface UserService {
    public SUser getUserByName(String username);
    public SUser saveUser(SUser user);
    public List<SUser> getAllUsers();
}
