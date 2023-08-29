package com.charliekriska.financeuserdata.service;

import com.charliekriska.financeuserdata.model.Savings;

public interface SavingsService {
    Savings getSavingsByUserId(int userId);
    Savings addSavings(Savings savings);
}
