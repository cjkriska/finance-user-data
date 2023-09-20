package com.charliekriska.financeuserdata.service;

import com.charliekriska.financeuserdata.dao.UserDao;
import com.charliekriska.financeuserdata.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User addUser(User user) {
        return userDao.addUser(user);
    }
}
