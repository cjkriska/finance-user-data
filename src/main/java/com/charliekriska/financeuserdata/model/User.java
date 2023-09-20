package com.charliekriska.financeuserdata.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private int userId;
    private String username;
    private String password;

}
