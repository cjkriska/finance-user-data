package com.charliekriska.financeuserdata.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Savings {

    public Savings() {
    }

    public Savings(int savingsDataId, int userId, double income, double expenses, double currentSavings, double expectedReturn) {
        this.savingsDataId = savingsDataId;
        this.userId = userId;
        this.income = income;
        this.expenses = expenses;
        this.currentSavings = currentSavings;
        this.expectedReturn = expectedReturn;
    }
    public Savings(int userId, double income, double expenses, double currentSavings, double expectedReturn) {
        this.userId = userId;
        this.income = income;
        this.expenses = expenses;
        this.currentSavings = currentSavings;
        this.expectedReturn = expectedReturn;
    }

    private int savingsDataId;
    private int userId;
    private double income;
    private double expenses;
    private double currentSavings;
    private double expectedReturn;

}
