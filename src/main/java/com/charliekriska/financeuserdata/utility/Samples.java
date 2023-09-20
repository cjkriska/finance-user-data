package com.charliekriska.financeuserdata.utility;

import com.charliekriska.financeuserdata.model.Savings;
import com.charliekriska.financeuserdata.model.User;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class Samples {

    public List<User> sampleUserList() {

        return List.of(
                User.builder()
                        .userId(1)
                        .username("sampleuser123")
                        .password("password123")
                        .build(),
                User.builder()
                        .userId(2)
                        .username("user234")
                        .password("pw2345")
                        .build(),
                User.builder()
                        .userId(3)
                        .username("user456")
                        .password("pass45678")
                        .build()
        );

    }

    public User sampleUserInput() {
        return User.builder()
                .username("sampleuser123")
                .password("password123")
                .build();
    }

    public User sampleUserOutput() {
        return User.builder()
                .userId(1)
                .username("sampleuser123")
                .password("password123")
                .build();
    }

    public Savings savingsSampleInput() {
        return Savings.builder()
                .userId(1)
                .income(50000)
                .expenses(35000)
                .currentSavings(5000)
                .expectedReturn(7)
                .build();
    }

    public Savings savingsSampleOutput() {
        return Savings.builder()
                .savingsDataId(2)
                .userId(1)
                .income(50000)
                .expenses(35000)
                .currentSavings(5000)
                .expectedReturn(7)
                .build();
    }

}
