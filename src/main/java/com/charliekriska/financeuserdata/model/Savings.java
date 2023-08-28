package com.charliekriska.financeuserdata.model;

import lombok.Data;

@Data
public class Savings {

    private int savingsDataId;
    private int userId;
    private double income;
    private double expenses;
    private double currentSavings;
    private double expectedReturn;

}
