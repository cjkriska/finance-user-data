package com.charliekriska.financeuserdata.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserController {

    @GetMapping("/user")
    public String getUser() {
        return "Sample User 3";
    }

}
