package com.charliekriska.financeuserdata.service;

import com.charliekriska.financeuserdata.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User addUser(User user);

}
