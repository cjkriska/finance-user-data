package com.charliekriska.financeuserdata.controller;

import com.charliekriska.financeuserdata.model.User;
import com.charliekriska.financeuserdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

}
